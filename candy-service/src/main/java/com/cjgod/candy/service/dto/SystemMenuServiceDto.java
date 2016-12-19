package com.cjgod.candy.service.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pei.li on 2016/11/7.
 */
public class SystemMenuServiceDto {
    private Integer id;
    private String url; //访问URL地址
    private String text; //菜单名称
    private Integer parentMenuId; //父菜单ID
    private String state; //点击状态
    private List<SystemMenuServiceDto> children = new ArrayList<SystemMenuServiceDto>(); //子菜单合集

    private SystemMenuAttributesServiceDto attributes = new SystemMenuAttributesServiceDto();

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<SystemMenuServiceDto> getChildren() {
        return children;
    }

    public void setChildren(List<SystemMenuServiceDto> children) {
        this.children = children;
    }

    public SystemMenuAttributesServiceDto getAttributes() {
        return attributes;
    }

    public void setAttributes(SystemMenuAttributesServiceDto attributes) {
        this.attributes = attributes;
    }
}
