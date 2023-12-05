package com.lzy.yangoj.judge.JudgeStrategy;

import com.lzy.yangoj.judge.model.JudgeInfo;
import com.lzy.yangoj.model.entity.uploadSolve;
import org.springframework.stereotype.Service;

/**
 * @author lzy
 * 判题策略全局管理器
 */

@Service
public class JudgeManager {

    public JudgeInfo doQuestionResultJudge(JudgeContext judgeContext){
        uploadSolve uploadSolve = judgeContext.getUploadSolve();
        String language = uploadSolve.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doQuestionResultJudge(judgeContext);
    }
}
