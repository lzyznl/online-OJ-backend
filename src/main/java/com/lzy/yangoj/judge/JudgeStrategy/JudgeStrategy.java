package com.lzy.yangoj.judge.JudgeStrategy;

import com.lzy.yangoj.judge.model.JudgeInfo;

/**
 * @author lzy
 * 判题策略接口
 */
public interface JudgeStrategy {

    JudgeInfo doQuestionResultJudge(JudgeContext judgeContext);
}
