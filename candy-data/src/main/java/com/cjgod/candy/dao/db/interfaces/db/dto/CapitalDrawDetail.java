package com.cjgod.candy.dao.db.interfaces.db.dto;

/**
 * Created by Administrator on 2016/12/14.
 */
public class CapitalDrawDetail {
    private String userName; //用户名
    private String drawStatus; //提现状态
    private String siteId; //所属站点
    private String amount; //提现金额
    private String drawType; //提款方式
    private String account; //汇款账户
    private String applyForDate; //申请时间
    private String acceptDate; //受理时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(String drawStatus) {
        this.drawStatus = drawStatus;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDrawType() {
        return drawType;
    }

    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getApplyForDate() {
        return applyForDate;
    }

    public void setApplyForDate(String applyForDate) {
        this.applyForDate = applyForDate;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }
}
