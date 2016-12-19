package com.cjgod.candy.dao.db.interfaces;


import com.cjgod.candy.dao.db.dto.SystemRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色DAO
 * Created by pei.li on 2016/9/23.
 */
@Repository
public interface SyetemRoleMapper {
    /**
     * 查找所有角色列表
     *
     * @return 角色列表
     */
    List<SystemRole> getRoleList();
}
