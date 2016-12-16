package com.cjgod.candy.model;

import com.cjgod.candy.enums.RetStatusEnum;

public class JsonResult extends BaseModel {
    /**
     * 针对request的回应结果
     */
    public String ret = RetStatusEnum.ok.name();
    /**
     * 客户端需要提示给用户的内容
     */
    public String msg = "";
    /**
     * 响应的结果内容
     */
    public Object content;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
