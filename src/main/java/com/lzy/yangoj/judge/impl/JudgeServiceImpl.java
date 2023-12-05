package com.lzy.yangoj.judge.impl;

import cn.hutool.json.JSONUtil;
import com.lzy.yangoj.common.ErrorCode;
import com.lzy.yangoj.exception.BusinessException;
import com.lzy.yangoj.judge.CodeSandbox;
import com.lzy.yangoj.judge.CodeSandboxs.CodeSandboxProxyFactory;
import com.lzy.yangoj.judge.CodeSandboxs.CodeSandboxsFactory;
import com.lzy.yangoj.judge.JudgeStrategy.JudgeContext;
import com.lzy.yangoj.judge.JudgeStrategy.JudgeManager;
import com.lzy.yangoj.judge.judgeService;
import com.lzy.yangoj.judge.model.ExecuteCodeRequest;
import com.lzy.yangoj.judge.model.ExecuteCodeResponse;
import com.lzy.yangoj.judge.model.JudgeInfo;
import com.lzy.yangoj.model.entity.JudgeCase;
import com.lzy.yangoj.model.entity.problem;
import com.lzy.yangoj.model.entity.uploadSolve;
import com.lzy.yangoj.model.enums.questionStatusEnum;
import com.lzy.yangoj.service.problemService;
import com.lzy.yangoj.service.uploadSolveService;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class JudgeServiceImpl implements judgeService {

    @Resource
    private problemService problemService;

    @Resource
    private uploadSolveService uploadSolveService;

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private JudgeManager judgeManager;

    /**
     * 调用代码沙箱进行判题
     * @param questionSubmitId
     * @return
     */
    @Override
    public uploadSolve doJudge(Long questionSubmitId) {
        //1.根据提交id获取到对应题目信息
        if(questionSubmitId==null||questionSubmitId<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"题目提交参数错误");
        }
        uploadSolve uploadSolve = uploadSolveService.getUploadSolveById(questionSubmitId);
        problem question = problemService.getSingleProblemById(uploadSolve.getProblemId());
        if(uploadSolve==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"题目提交记录不存在");
        }
        //2:判断题目当前状态是否是等待中，如果是等待中则不需要重复执行
        if(uploadSolve.getStatus()== questionStatusEnum.WAITING.getValue()){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该题目正在判题中");
        }
        //3:更改题目信息为判题中,防止用户多次重复提交，也便于用户能够及时看到对应题目的状态信息
        uploadSolve newUploadSolve = new uploadSolve();
        newUploadSolve.setId(questionSubmitId);
        newUploadSolve.setStatus(questionStatusEnum.RUNNING.getValue());
        boolean fact = uploadSolveService.updateById(newUploadSolve);
        if(!fact){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新题目状态失败");
        }
        //4.调用代码沙箱，获取到判题结果
        CodeSandbox codeSandbox = CodeSandboxsFactory.newInstance(type);
        codeSandbox=new CodeSandboxProxyFactory(codeSandbox);
        //获取到对应的输入样例
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCases = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCases.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(uploadSolve.getCode())
                .codeLanguage(uploadSolve.getLanguage())
                .inputList(inputList).build();
        //代码沙箱进行执行，执行完成后返回对应的执行结果
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        //5.将执行结果与预计输出样例进行比对（采用不同判题策略）
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCases);
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setQuestion(question);
        judgeContext.setUploadSolve(uploadSolve);
        //此处采用的是策略模式，创建了一个默认判题策略
        JudgeInfo judgeInfoResponse = judgeManager.doQuestionResultJudge(judgeContext);
        //将判题后的结果再次更新到数据库
        newUploadSolve = new uploadSolve();
        newUploadSolve.setId(questionSubmitId);
        newUploadSolve.setStatus(questionStatusEnum.RUNNING.getValue());
        newUploadSolve.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoResponse));
        boolean UpdateFact = uploadSolveService.updateById(newUploadSolve);
        if(!UpdateFact){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"判题结果信息更新失败");
        }
        return uploadSolveService.getUploadSolveById(questionSubmitId);
    }
}
