package com.cjgod.candy.morning.model;

import java.math.BigDecimal;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class PlatformOperationRecordModel {
    private BigDecimal uid;
    private String     ip;
    private String     action;
    private String     params;
    private String     description;
    private String     optTime;

    public BigDecimal getUid() {
        return uid;
    }

    public void setUid(BigDecimal uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }
}
