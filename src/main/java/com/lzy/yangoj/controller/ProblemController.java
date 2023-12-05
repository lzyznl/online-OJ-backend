package com.lzy.yangoj.controller;

import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.yangoj.common.BaseResponse;
import com.lzy.yangoj.common.ErrorCode;
import com.lzy.yangoj.common.ResultUtils;
import com.lzy.yangoj.constant.UserConstant;
import com.lzy.yangoj.exception.BusinessException;
import com.lzy.yangoj.model.dto.problem.GetProblemRequest;
import com.lzy.yangoj.model.dto.problem.addProblemRequest;
import com.lzy.yangoj.model.entity.User;
import com.lzy.yangoj.model.entity.problem;
import com.lzy.yangoj.service.problemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目相关接口
 *
 * @author lzy
 */
@RestController
@RequestMapping("/problem")
@Slf4j
public class ProblemController {

    @Resource
    private problemService problemService;

    /**
     * 对用户权限进行校验
     * @param request
     */
    User checkAdmin(HttpServletRequest request){
        User loginUser = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if(loginUser==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (!loginUser.getUserRole().equals(UserConstant.ADMIN_ROLE)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return loginUser;
    }


    @PostMapping("/add")
    public BaseResponse<Boolean> addProblem(@RequestBody addProblemRequest addProblemRequest, HttpServletRequest httpServletRequest){
        User loginUser = checkAdmin(httpServletRequest);
        String problemAnswer = addProblemRequest.getProblemAnswer();
        String problemContent = addProblemRequest.getProblemContent();
        String problemTags = addProblemRequest.getProblemTags();
        String problemJudgeCase = addProblemRequest.getProblemJudgeCase();
        String problemJudgeConfig = addProblemRequest.getProblemJudgeConfig();
        String problemTitle = addProblemRequest.getProblemTitle();

        if(StringUtils.isAnyEmpty(problemAnswer,problemContent,problemTags,problemJudgeConfig,problemJudgeCase,problemTitle)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        else if (StringUtils.isAnyBlank(problemAnswer,problemContent,problemTags,problemJudgeConfig,problemJudgeCase,problemTitle)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Boolean fact = problemService.add_problem(problemAnswer,problemContent,problemTags,problemJudgeConfig,problemJudgeCase,problemTitle,loginUser);
        return fact?ResultUtils.success(fact):ResultUtils.error(ErrorCode.OPERATION_ERROR);

    }

    @PostMapping("/get")
    BaseResponse<Page<problem>> getProblem(@RequestBody GetProblemRequest getProblemRequest, HttpServletRequest httpServletRequest){
        int pageSize = getProblemRequest.getPageSize();
        int current = getProblemRequest.getCurrent();
        if(current<=0||pageSize<=0){
            return null;
        }
        User user = checkAdmin(httpServletRequest);
        Page<problem> problemVoPage = problemService.page(new Page<>(current,pageSize),
                null);
        return ResultUtils.success(problemVoPage);
    }
}
