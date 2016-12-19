package com.cjgod.candy.dao.db.interfaces;

/**
 * Created by lichunjiang on 2016/12/19.
 */

import com.cjgod.candy.dao.db.dto.SystemAdministrator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SystemAdministratorMapper {
    /**
     * 根据用户名称查询用户是否存在，登录
     *
     * @param username 用户名称
     * @param password 密码
     * @return SystemAdministrator系统管理员信息
     */
    SystemAdministrator getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据系统ID查询系统管理员
     *
     * @param id 系统管理员ID
     * @return SystemAdministrator系统管理员信息
     */
    SystemAdministrator getById(@Param("id") Integer id);

    /**
     * 根据系统管理员ID和修改的密码进行密码更新
     *
     * @param id       系统管理员ID
     * @param password 修改的密码
     */
    void updatePasswordById(@Param("id") Integer id, @Param("password") String password);

    /**
     * 查询系统管理员分页
     *
     * @param page     当前页数
     * @param rows     每页显示多少条
     * @param username 查询的用户名称
     * @return List<SystemAdministrator>系统管理员分页
     */
    List<SystemAdministrator> getPageByCondition(@Param("page") Integer page, @Param("rows") Integer rows, @Param("username") String username);

    /**
     * 查询系统管理员分页总页数
     *
     * @param username 查询的用户名称
     * @return 查询系统管理员分页总页数
     */
    int getPageCounts(@Param("username") String username);

    /**
     * 删除管理员帐号
     *
     * @param id 管理员帐号ID集合
     */
    void deleteByIds(int[] id);

    /**
     * 修改管理员信息
     *
     * @param systemAdministrator 管理员信息
     */
    void update(SystemAdministrator systemAdministrator);

    /**
     * 新增管理员信息
     *
     * @param systemAdministrator 管理员信息
     */
    void add(SystemAdministrator systemAdministrator);

    /**
     * 根据用户名称查询用户是否已经存在
     *
     * @param username 查询的管理员名称
     * @return 管理员信息
     */
    SystemAdministrator getByUsername(@Param("username") String username);
}

