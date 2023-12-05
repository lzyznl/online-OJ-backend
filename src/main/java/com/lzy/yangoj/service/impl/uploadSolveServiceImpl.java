package com.lzy.yangoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.yangoj.common.ErrorCode;
import com.lzy.yangoj.exception.BusinessException;
import com.lzy.yangoj.model.entity.User;
import com.lzy.yangoj.model.entity.problem;
import com.lzy.yangoj.model.entity.uploadSolve;
import com.lzy.yangoj.model.enums.questionStatusEnum;
import com.lzy.yangoj.service.uploadSolveService;
import com.lzy.yangoj.mapper.uploadSolveMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 86185
* @description 针对表【uploadSolve(题目提交表)】的数据库操作Service实现
* @createDate 2023-11-25 14:07:12
*/
@Service
public class uploadSolveServiceImpl extends ServiceImpl<uploadSolveMapper, uploadSolve>
    implements uploadSolveService{

    @Resource
    private uploadSolveMapper uploadSolveMapper;

    @Override
    public Boolean SaveAndJudge(Long questionId, String code, String codeLanguage, User loginUser) {
        // 1.获取相关信息存入数据库
        Long loginUserId = loginUser.getId();
        uploadSolve uploadSolve = new uploadSolve();
        uploadSolve.setUserId(loginUserId);
        uploadSolve.setProblemId(questionId);
        uploadSolve.setLanguage(codeLanguage);
        uploadSolve.setCode(code);
        uploadSolve.setStatus(questionStatusEnum.WAITING.getValue());
        int insert = uploadSolveMapper.insert(uploadSolve);

        //todo:需要异步进行判题

        //返回结果
        return insert>0;
    }

    @Override
    public uploadSolve getUploadSolveById(long id) {
        if(id<0){
            return null;
        }
        return uploadSolveMapper.selectById(id);
    }
}




