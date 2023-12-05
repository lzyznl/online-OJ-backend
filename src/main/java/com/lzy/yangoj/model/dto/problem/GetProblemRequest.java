package com.lzy.yangoj.model.dto.problem;

import lombok.Data;

@Data
public class GetProblemRequest {
    private int pageSize;
    private int current;
}
