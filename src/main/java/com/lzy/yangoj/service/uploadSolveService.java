package com.lzy.yangoj.service;

import com.lzy.yangoj.model.entity.User;
import com.lzy.yangoj.model.entity.uploadSolve;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86185
* @description 针对表【uploadSolve(题目提交表)】的数据库操作Service
* @createDate 2023-11-25 14:07:12
*/
public interface uploadSolveService extends IService<uploadSolve> {

    /**
     * 处理用户的判题请求
     * @param questionId
     * @param code
     * @param codeLanguage
     * @param loginUser
     * @return
     */
    Boolean SaveAndJudge(Long questionId, String code, String codeLanguage, User loginUser);

    /**
     * 根据id获取单个提交记录
     * @param id
     * @return
     */
    uploadSolve getUploadSolveById(long id);
}
