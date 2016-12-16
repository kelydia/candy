package com.cjgod.candy.enums;

/**
 * 收支类型 预存款/奖金/退款/购彩/提款/扣款
 * Created by ruchao.zou on 2016/10/19.
 *
 * @since 1.0
 */
public enum IAETypeEnum {
    /**
     * 预存款
     */
    DEPOSIT(0, "预存"),
    /**
     * 奖金
     */
    BONUS(0, "奖金"),
    /**
     * 退款
     */
    REFUND(0, "退款"),
    /**
     * 购彩
     */
    LOTTERY(1, "购彩"),
    /**
     * 提款
     */
    WITHDRAW(1, "提款"),
    /**
     * 扣款
     */
    DEDUCT(1, "扣款");
    //0代表收入，1代表支出
    private int value;
    //描述
    private String desc;

    IAETypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static IAETypeEnum fromName(String name) {
        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据Name值获取描述信息
     *
     * @param name 枚举name值
     * @return 枚举描述信息
     */
    public static String getDesc(String name) {
        try {
            return valueOf(name).getDesc();
        } catch (Exception e) {
            return "";
        }
    }
}
