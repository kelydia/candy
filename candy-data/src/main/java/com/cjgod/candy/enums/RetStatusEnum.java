package com.cjgod.candy.enums;

/**
 * JSON相应枚举
 */
public enum RetStatusEnum {
    /**
     * 业务请求服务端处理成功
     */
    ok,
    /**
     * 表示请求业务层处理失败，具体原因在msg字段中
     */
    failed,
    /**
     * 表示非法request请求
     */
    unauthorized,
    /**
     * PC Web端表示session超时，提示重新登录
     */
    clear
}
