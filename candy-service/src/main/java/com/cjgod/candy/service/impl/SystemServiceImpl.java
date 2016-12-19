package com.cjgod.candy.service.impl;

import com.cjgod.candy.common.constant.ServiceConstant;
import com.cjgod.candy.common.exception.CandyBusinessException;
import com.cjgod.candy.common.exception.CandyParameterException;
import com.cjgod.candy.common.util.Md5Util;
import com.cjgod.candy.dao.db.dto.SystemAdministrator;
import com.cjgod.candy.dao.db.interfaces.SystemAdministratorMapper;
import com.cjgod.candy.enums.ResultStatusEnum;
import com.cjgod.candy.service.dto.SystemAdminServiceDto;
import com.cjgod.candy.service.interfaces.SystemService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemAdministratorMapper systemAdminLogic;

    @Override
    public SystemAdminServiceDto findAdmin(Integer id) {
        if (id == null || id == 0) {
            throw new CandyParameterException("null", new String[]{"ID"});
        }
        SystemAdminServiceDto systemAdminServiceDto = new SystemAdminServiceDto();
        SystemAdministrator systemAdminLogicDto = systemAdminLogic.getById(id);
        if (systemAdminLogicDto == null) {
            throw new CandyParameterException("null", new String[]{"用户"});
        }
        BeanUtils.copyProperties(systemAdminLogicDto, systemAdminServiceDto);

        return systemAdminServiceDto;
    }


    @Override
    public List<SystemAdminServiceDto> getPages(Integer page, Integer rows, String username) {
        List<SystemAdminServiceDto> systemAdminServiceDtoList = new ArrayList<>();

        if (page == null) {
            page = ServiceConstant.PAGE;
        }
        if (rows == null) {
            rows = ServiceConstant.ROWS;
        }
        if (username == null) {
            username = "";
        }
        List<SystemAdministrator> systemAdminLogicDtoList = systemAdminLogic.getPageByCondition((page - 1) * rows, rows, username);

        for (SystemAdministrator systemAdminLogicDto : systemAdminLogicDtoList) {
            SystemAdminServiceDto systemAdminServiceDto = new SystemAdminServiceDto();
            BeanUtils.copyProperties(systemAdminLogicDto, systemAdminServiceDto);
            systemAdminServiceDtoList.add(systemAdminServiceDto);
        }
        return systemAdminServiceDtoList;
    }

    @Override
    public Integer getPageCounts(String username) {
        if (username == null) {
            username = "";
        }
        return systemAdminLogic.getPageCounts(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(String ids) {
        if (StringUtils.isBlank(ids)) {
            throw new CandyParameterException("null", new String[]{"用户ID"});
        }
        JSONArray jsonArray;
        try {
            jsonArray = JSONArray.fromObject(ids);
        } catch (Exception e) {
            throw new CandyBusinessException("convert-json-error");
        }

        int[] idItem = new int[jsonArray.size()];

        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject ob = jsonArray.getJSONObject(j);
            idItem[j] = Integer.parseInt(ob.get("id").toString());
        }
        systemAdminLogic.deleteByIds(idItem);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(SystemAdminServiceDto systemAdminServiceDto) {
        if (systemAdminServiceDto == null) {
            throw new CandyParameterException("null", new String[]{"管理员"});
        }
        SystemAdministrator systemAdminLogicDto = new SystemAdministrator();

        String password = systemAdminServiceDto.getPassword();
        Integer id = systemAdminServiceDto.getId();

        if (StringUtils.isNotBlank(password)) {
            systemAdminServiceDto.setPassword(Md5Util.string2Md5(password));
        }
        if ("●●●●●●".equals(password)) {
            systemAdminServiceDto.setPassword(null);
        }
        BeanUtils.copyProperties(systemAdminServiceDto, systemAdminLogicDto);
        if (id == null || id == 0) {
            systemAdminLogic.add(systemAdminLogicDto);
        } else {
            systemAdminLogic.update(systemAdminLogicDto);
        }
    }

    @Override
    public String repeatAdminName(String username) {
        if (StringUtils.isBlank(username)) {
            return ResultStatusEnum.ok.name();
        }
        SystemAdministrator systemAdminLogicDto = systemAdminLogic.getByUsername(username);

        if (systemAdminLogicDto != null) {
            return ResultStatusEnum.failed.name();
        }
        return ResultStatusEnum.ok.name();
    }
}
