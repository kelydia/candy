package com.cjgod.candy.enums;

/**
 * Created by lichunjiang on 2016/12/19.
 */
/**
 * 管理员状态枚举
 */
public enum AdminStatusEnum {
    /**
     * 会员状态正常
     */
    ENABLED("正常"),

    /**
     * 会员状态禁用
     */
    DISABLE("禁用");

    private String description;

    public String getDescription() {
        return description;
    }

    private AdminStatusEnum(String description) {
        this.description = description;
    }
}

