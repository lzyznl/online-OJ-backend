package com.lzy.yangoj.model.entity;

import lombok.Data;

@Data
public class JudgeConfig {
    /**
     * 内存限制
     */
    private String memoryLimit;

    /**
     * 时间限制
     */
    private String timeLimit;

    /**
     * 堆栈限制
     */
    private String stackLimit;
}
