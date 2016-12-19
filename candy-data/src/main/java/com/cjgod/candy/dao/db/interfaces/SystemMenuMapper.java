package com.cjgod.candy.dao.db.interfaces;

import com.cjgod.candy.dao.db.dto.SystemMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单DAO
 * Created by pei.li on 2016/9/21.
 */
@Repository
public interface SystemMenuMapper {
    /**
     * 根据管理员角色ID查询菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<SystemMenu> getMenuListByRoleId(@Param("roleId") Integer roleId);
}
