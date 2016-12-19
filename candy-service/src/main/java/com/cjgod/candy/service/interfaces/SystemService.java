package com.cjgod.candy.service.interfaces;

import com.cjgod.candy.service.dto.SystemAdminServiceDto;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public interface SystemService {
    /**
     * 通过ID查找管理员信息
     *
     * @param id
     * @return
     */
    SystemAdminServiceDto findAdmin(Integer id);


    /**
     * 查询分页
     *
     * @param page
     * @param rows
     * @param username
     * @return
     */
    List<SystemAdminServiceDto> getPages(Integer page, Integer rows, String username);

    /**
     * 获取查询分页的总页数
     *
     * @param username
     * @return
     */
    Integer getPageCounts(String username);

    /**
     * 根据管理员ID进行删除
     *
     * @param ids 删除的id集合
     */
    void deleteByIds(String ids);


    /**
     * 保存或者修改管理员
     *
     * @param systemAdminServiceDto 管理员对象
     */
    void save(SystemAdminServiceDto systemAdminServiceDto);

    /**
     * 查询用户名是否存在
     *
     * @param username 管理员名称
     */
    String repeatAdminName(String username);
}
