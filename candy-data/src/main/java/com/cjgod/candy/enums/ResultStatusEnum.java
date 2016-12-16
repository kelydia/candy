package com.cjgod.candy.enums;

/**
 * JSON相应枚举
 */
public enum ResultStatusEnum {
    /** 业务请求服务端处理成功 */
    ok,
    /** 表示请求业务层处理失败，具体原因在msg字段中 */
    failed,
    /** 表示登录状态异常，提示用户重新登录 */
    need_login,
    /** 表示客户端版本需要更新，但用户可以暂不更新，继续使用当前版本 */
    update,
    /** 表示客户端版本需要强制更新 */
    update_force,
    /** PC Web端表示session超时，提示重新登录 ；app端表示需要清楚客户端本地缓存，重新登录 */
    clear
}
