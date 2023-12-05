package com.lzy.yangoj.model.dto.solve;

import lombok.Data;

@Data
public class uploadSolveRequest {

    private Long questionId;

    private String code;

    private String codeLanguage;
}
