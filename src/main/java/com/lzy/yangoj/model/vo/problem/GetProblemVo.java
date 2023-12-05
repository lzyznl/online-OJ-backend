package com.lzy.yangoj.model.vo.problem;

import lombok.Data;

@Data
public class GetProblemVo {
    private long id;
    private String title;
    private String content;
    private String tags;
    private String answer;
    private int uploadNum;
    private int acceptedSolveNum;
    private String judgeConfig;
    private String judgeCase;
    private long userId;
    private String createTime;
}
