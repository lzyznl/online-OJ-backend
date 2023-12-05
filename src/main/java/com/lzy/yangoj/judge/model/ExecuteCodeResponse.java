package com.lzy.yangoj.judge.model;

import lombok.Data;

import java.util.List;

@Data
public class ExecuteCodeResponse {
    private List<String> outputList;
    private Integer status;
    private String message;
    private JudgeInfo judgeInfo;
}
