package com.cjgod.candy.dao.db.dto;

/**
 * 系统角色数据体
 * Created by pei.li on 2016/9/23.
 */
public class SystemRole {
    private Integer id;
    private String name; //角色名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SystemRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
