package com.lzy.yangoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.yangoj.model.dto.problem.GetProblemRequest;
import com.lzy.yangoj.model.entity.User;
import com.lzy.yangoj.model.entity.problem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.yangoj.model.vo.problem.GetProblemVo;

import java.util.List;

/**
* @author 86185
* @description 针对表【problem(题目表)】的数据库操作Service
 *@author lzy
* @createDate 2023-11-25 14:01:44
*/
public interface problemService extends IService<problem> {



    /**
     * 添加题目
     * @param problemAnswer
     * @param problemContent
     * @param problemTags
     * @param problemJudgeConfig
     * @param problemJudgeCase
     * @param problemTitle
     * @param loginUser
     * @return
     */
    Boolean add_problem(String problemAnswer, String problemContent, String problemTags, String problemJudgeConfig, String problemJudgeCase, String problemTitle, User loginUser);

    /**
     * 分页获取题目信息
     * @param pageSize
     * @param current
     * @return
     */
    List<GetProblemVo> get_problem(int pageSize,int current);


    /**
     * 获取单个题目相关信息
     * @param problemId
     * @return
     */
    problem getSingleProblemById(long problemId);

}
