package com.lzy.yangoj.controller;


import com.lzy.yangoj.common.BaseResponse;
import com.lzy.yangoj.common.ErrorCode;
import com.lzy.yangoj.common.ResultUtils;
import com.lzy.yangoj.exception.BusinessException;
import com.lzy.yangoj.model.dto.solve.uploadSolveRequest;
import com.lzy.yangoj.model.entity.User;
import com.lzy.yangoj.service.UserService;
import com.lzy.yangoj.service.uploadSolveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/solve")
@Slf4j
public class SolveQuestionController {

    @Resource
    private uploadSolveService uploadSolveService;

    @Resource
    private UserService userService;

    @PostMapping("/upload")
    public BaseResponse<Boolean> uploadSolve(@RequestBody uploadSolveRequest uploadSolveRequest, HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        if(uploadSolveRequest==null||loginUser==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long questionId = uploadSolveRequest.getQuestionId();
        String code = uploadSolveRequest.getCode();
        String codeLanguage = uploadSolveRequest.getCodeLanguage();
        if(questionId==null||code==null||codeLanguage==null){
            throw  new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(questionId<0||code.equals("")||codeLanguage.equals("")){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean fact = uploadSolveService.SaveAndJudge(questionId, code, codeLanguage, loginUser);
        return ResultUtils.success(fact);
    }
}
