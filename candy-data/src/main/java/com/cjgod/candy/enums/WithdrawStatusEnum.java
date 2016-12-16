package com.cjgod.candy.enums;


public enum WithdrawStatusEnum {
    /**
     * 提现中
     */
    withdraw_start("提现申请中"),
    /**
     * 同意提现
     */
    withdraw_accept("同意提现"),
    /**
     * 已提现
     */
    withdraw_success("已提现"),
    /**
     * 提现失败
     */
    withdraw_failed("提现失败"),
    /**
     * 拒绝提款
     */
    withdraw_reject("拒绝提款");

    private String desc;

    WithdrawStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static WithdrawStatusEnum fromName(String name) {
        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }
}
