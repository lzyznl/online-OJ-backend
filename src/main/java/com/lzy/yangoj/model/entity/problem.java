package com.lzy.yangoj.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 题目表
 * @author lzy
 * @TableName problem
 */
@TableName(value ="problem")
@Data
public class problem implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 题目创建者id
     */
    private Long userId;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目答案
     */
    private String answer;
    /**
     * 题目标签列表（json 数组）
     */
    private String tags;

    /**
     * 题目解题提交数
     */
    private Integer uploadNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置(json格式)
     */
    private String judgeConfig;

    /**
     * 判题测试用例
     */
    private String judgeCase;

    /**
     * 题目通过率
     */
    private String passPercent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}