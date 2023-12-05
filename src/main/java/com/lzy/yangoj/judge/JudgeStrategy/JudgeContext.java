package com.lzy.yangoj.judge.JudgeStrategy;

import com.lzy.yangoj.judge.model.JudgeInfo;
import com.lzy.yangoj.model.entity.JudgeCase;
import com.lzy.yangoj.model.entity.problem;
import com.lzy.yangoj.model.entity.uploadSolve;
import lombok.Data;


import java.util.List;

/**
 * 判题策略上下文
 */
@Data
public class JudgeContext {

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private JudgeInfo judgeInfo;

    private problem question;

    private uploadSolve uploadSolve;
}
