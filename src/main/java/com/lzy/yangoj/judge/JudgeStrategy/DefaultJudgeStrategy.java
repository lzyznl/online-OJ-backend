package com.lzy.yangoj.judge.JudgeStrategy;

import cn.hutool.json.JSONUtil;
import com.lzy.yangoj.judge.model.JudgeInfo;
import com.lzy.yangoj.model.entity.JudgeCase;
import com.lzy.yangoj.model.entity.JudgeConfig;
import com.lzy.yangoj.model.entity.problem;
import com.lzy.yangoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doQuestionResultJudge(JudgeContext judgeContext) {
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        problem question = judgeContext.getQuestion();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(judgeInfo.getMemory());
        judgeInfoResponse.setTime(judgeInfo.getTime());

        //比对结果是否一致
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getText());
        if(outputList.size()!= inputList.size()){
            judgeInfoMessageEnum=JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getText());
            return judgeInfoResponse;
        }
        for(int i=0;i<judgeCaseList.size();++i){
            JudgeCase singleJudgeCase = judgeCaseList.get(i);
            if(!singleJudgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoMessageEnum=JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getText());
                return judgeInfoResponse;
            }
        }
        //比对程序运行是否超过题目限制
        String outputTime = judgeInfo.getTime();
        String outputMemory = judgeInfo.getMemory();

        String judgeConfig = question.getJudgeConfig();
        JudgeConfig questionOriginalConfig = JSONUtil.toBean(judgeConfig, JudgeConfig.class);
        String memoryLimit = questionOriginalConfig.getMemoryLimit();
        String timeLimit = questionOriginalConfig.getTimeLimit();
        if(Integer.parseInt(outputTime)>Integer.parseInt(timeLimit)){
            judgeInfoMessageEnum=JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getText());
            return judgeInfoResponse;
        }
        if(Integer.parseInt(outputMemory)>Integer.parseInt(memoryLimit)){
            judgeInfoMessageEnum=JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getText());
            return judgeInfoResponse;
        }
        return judgeInfoResponse;
    }
}
