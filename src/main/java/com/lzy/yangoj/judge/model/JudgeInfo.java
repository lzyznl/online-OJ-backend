package com.lzy.yangoj.judge.model;

import lombok.Data;

@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 程序执行时间
     */
    private String time;

    /**
     * 程序执行消耗内存
     */
    private String memory;
}
