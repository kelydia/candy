package com.cjgod.candy.dao.db.dto;

/**
 * 系统菜单数据体
 * Created by pei.li on 2016/9/21.
 */
public class SystemMenu {
    private Integer id;
    private String url; //访问URL地址
    private String text; //菜单名称
    private Integer parentMenuId; //父菜单ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    @Override
    public String toString() {
        return "SystemMenu{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", parentMenuId=" + parentMenuId +
                '}';
    }
}
