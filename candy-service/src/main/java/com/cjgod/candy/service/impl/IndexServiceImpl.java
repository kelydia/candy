package com.cjgod.candy.service.impl;

import com.cjgod.candy.common.exception.CandyBusinessException;
import com.cjgod.candy.common.exception.CandyParameterException;
import com.cjgod.candy.common.util.Md5Util;
import com.cjgod.candy.dao.db.dto.SystemAdministrator;
import com.cjgod.candy.dao.db.dto.SystemMenu;
import com.cjgod.candy.dao.db.interfaces.SystemAdministratorMapper;
import com.cjgod.candy.dao.db.interfaces.SystemMenuMapper;
import com.cjgod.candy.enums.AdminStatusEnum;
import com.cjgod.candy.service.dto.SystemAdminServiceDto;
import com.cjgod.candy.service.dto.SystemMenuServiceDto;
import com.cjgod.candy.service.interfaces.IndexService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pei.li on 2016/11/8.
 */
@Service
public class IndexServiceImpl implements IndexService {

    private SystemMenuMapper systemMenuLogic;
    private SystemAdministratorMapper systemAdminLogic;

    @Autowired
    public IndexServiceImpl(SystemMenuMapper systemMenuLogic, SystemAdministratorMapper systemAdminLogic) {
        this.systemMenuLogic = systemMenuLogic;
        this.systemAdminLogic = systemAdminLogic;
    }

    @Override
    public SystemAdminServiceDto login(String username, String password) {
        if (StringUtils.isBlank(username)) {
            throw new CandyParameterException("null",new String[]{"用户名"});
        }
        if (StringUtils.isBlank(password)) {
            throw new CandyParameterException("null", new String[]{"密码"});
        }

        SystemAdminServiceDto systemAdminServiceDto = new SystemAdminServiceDto();

        SystemAdministrator adminCheck = systemAdminLogic.getByUsername(username);

        if (adminCheck == null) {
            throw new CandyBusinessException("login-user-null");
        }
        if (AdminStatusEnum.DISABLE.name().equals(adminCheck.getStatus())) {
            throw new CandyBusinessException("login-user-disable");
        }
        SystemAdministrator adminLogin = systemAdminLogic.getByUsernameAndPassword(username, Md5Util.string2Md5(password));
        if (adminLogin == null) {
            throw new CandyBusinessException("login-password-error");
        }
        BeanUtils.copyProperties(adminLogin, systemAdminServiceDto);

        return systemAdminServiceDto;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(Integer id, String oldPassword, String newPassword) {
        if (id == null || id == 0) {
            throw new CandyParameterException("null", new String[]{"用户ID"});
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new CandyParameterException("null", new String[]{"旧密码"});
        }
        if (StringUtils.isBlank(oldPassword)) {
            throw new CandyParameterException("null", new String[]{"新密码"});
        }

        SystemAdministrator systemAdminLogicDto = systemAdminLogic.getById(id);

        if (!systemAdminLogicDto.getPassword().equals(Md5Util.string2Md5(oldPassword))) {
            throw new CandyBusinessException("password-not-match");
        }

        systemAdminLogic.updatePasswordById(id, Md5Util.string2Md5(newPassword));
    }

    @Override
    public List<SystemMenuServiceDto> getMenu(Integer uid) {
        if (uid == null || uid == 0) {
            throw new CandyParameterException("null", new String[]{"ID"});
        }

        SystemAdministrator systemAdminLogicDto = systemAdminLogic.getById(uid);
        if (systemAdminLogicDto == null) {
            throw new CandyParameterException("null",new String[]{"用户"});
        }

        List<SystemMenuServiceDto> systemMenuServiceDtoList = new ArrayList<>();
        List<SystemMenu> systemMenuLogicDtoList = systemMenuLogic.getMenuListByRoleId(systemAdminLogicDto.getRoleId());

        for (SystemMenu systemMenu : systemMenuLogicDtoList) {
            SystemMenuServiceDto boxResult = new SystemMenuServiceDto();
            BeanUtils.copyProperties(systemMenu, boxResult);
            systemMenuServiceDtoList.add(boxResult);
        }
        List<SystemMenuServiceDto> menuList = formatTree(systemMenuServiceDtoList);
        return menuList;
    }

    private static List<SystemMenuServiceDto> formatTree(List<SystemMenuServiceDto> list) {

        SystemMenuServiceDto root = new SystemMenuServiceDto();
        SystemMenuServiceDto node = new SystemMenuServiceDto();
        List<SystemMenuServiceDto> treelist = new ArrayList<>();// 拼凑好的json格式的数据
        List<SystemMenuServiceDto> parentnodes = new ArrayList<>();// parentnodes存放所有的父节点

        if (list != null && list.size() > 0) {
            root = list.get(0);

            // 循环遍历oracle树查询的所有节点
            for (int i = 1; i < list.size(); i++) {
                node = list.get(i);
                node.getAttributes().setUrl(node.getUrl());
                if (node.getParentMenuId().equals(root.getId())) {
                    // 为tree root 增加子节点
                    parentnodes.add(node);
                    root.getChildren().add(node);
                } else {// 获取root子节点的孩子节点
                    getChildrenNodes(parentnodes, node);
                    parentnodes.add(node);
                }
            }
        }
        treelist.add(root);
        return treelist;

    }

    private static void getChildrenNodes(List<SystemMenuServiceDto> parentnodes, SystemMenuServiceDto node) {
        // 循环遍历所有父节点和node进行匹配，确定父子关系
        for (int i = parentnodes.size() - 1; i >= 0; i--) {

            SystemMenuServiceDto pnode = parentnodes.get(i);
            // 如果是父子关系，为父节点增加子节点，退出for循环
            if (pnode.getId().equals(node.getParentMenuId())) {
                pnode.setState("closed");// 关闭二级树
                pnode.getChildren().add(node);
                return;
            } else {
                // 如果不是父子关系，删除父节点栈里当前的节点，
                // 继续此次循环，直到确定父子关系或不存在退出for循环
                parentnodes.remove(i);
            }
        }
    }

}
