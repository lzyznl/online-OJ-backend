package com.lzy.yangoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.yangoj.common.ErrorCode;
import com.lzy.yangoj.exception.BusinessException;
import com.lzy.yangoj.model.entity.User;
import com.lzy.yangoj.model.entity.problem;
import com.lzy.yangoj.model.vo.problem.GetProblemVo;
import com.lzy.yangoj.service.problemService;
import com.lzy.yangoj.mapper.problemMapper;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 86185
* @description 针对表【problem(题目表)】的数据库操作Service实现
* @createDate 2023-11-25 14:01:44
*/
@Service
public class problemServiceImpl extends ServiceImpl<problemMapper, problem>
    implements problemService{

    @Resource
    private problemMapper problemMapper;

    @Override
    public Boolean add_problem(String problemAnswer, String problemContent, String problemTags, String problemJudgeConfig, String problemJudgeCase, String problemTitle, User loginUser) {
        problem problem = new problem();
        problem.setContent(problemContent);
        problem.setTitle(problemTitle);
        problem.setAcceptedNum(0);
        problem.setUploadNum(0);
        problem.setPassPercent("0%");
        problem.setJudgeCase(problemJudgeCase);
        problem.setJudgeConfig(problemJudgeConfig);
        problem.setTags(problemTags);
        problem.setAnswer(problemAnswer);
        problem.setUserId(loginUser.getId());

        int insert = problemMapper.insert(problem);
        return insert > 0;
    }

    @Override
    public List<GetProblemVo> get_problem(int pageSize, int current) {
        return null;
    }

    @Override
    public problem getSingleProblemById(long problemId) {
        if(problemId<0){
            return null;
        }
        problem problem = problemMapper.selectById(problemId);
        if(problem==null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"没有对应题目");
        }
        return problem;
    }
}




