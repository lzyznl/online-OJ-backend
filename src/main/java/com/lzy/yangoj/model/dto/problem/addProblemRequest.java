package com.lzy.yangoj.model.dto.problem;


import lombok.Data;

@Data
public class addProblemRequest {
    private String problemTitle;
    private String problemContent;
    private String problemAnswer;
    private String problemTags;
    private String problemJudgeConfig;
    private String problemJudgeCase;
}
