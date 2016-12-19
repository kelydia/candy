package com.cjgod.candy.service.interfaces;

import com.cjgod.candy.service.dto.SystemAdminServiceDto;
import com.cjgod.candy.service.dto.SystemMenuServiceDto;

import java.util.List;

/**
 * Created by pei.li on 2016/11/8.
 */
public interface IndexService {
    /**
     * 根据用户ID获取菜单
     *
     * @param uid
     * @return
     */
    List<SystemMenuServiceDto> getMenu(Integer uid);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    SystemAdminServiceDto login(String username, String password);

    /**
     * 修改登录的用户密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(Integer id, String oldPassword, String newPassword);
}
