package com.cjgod.candy.service.dto;

import com.cjgod.candy.enums.WithdrawStatusEnum;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/12/14.
 */
public class CapitalDrawDetailServiceDto {
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
        if(this.drawStatus != null)
            return WithdrawStatusEnum.fromName(this.drawStatus).getDesc();
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
