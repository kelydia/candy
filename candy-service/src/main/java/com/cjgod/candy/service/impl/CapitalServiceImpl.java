package com.cjgod.candy.service.impl;

import com.cjgod.candy.dao.db.dto.CapitalDrawDetail;
import com.cjgod.candy.dao.db.interfaces.CapitalMapper;
import com.cjgod.candy.service.dto.CapitalDrawDetailServiceDto;
import com.cjgod.candy.service.interfaces.CapitalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 * 资金管理服务
 */
@Service
public class CapitalServiceImpl implements CapitalService {
    @Autowired
    private CapitalMapper capitalMapper;

    @Override
    public Integer getWithdrawPageCounts(@Param("userName") String userName, @Param("siteId") String siteId, @Param("drawStatus") String drawStatus) {
        return capitalMapper.getWithdrawPageCounts(userName,siteId,drawStatus);
    }

    @Override
    public List<CapitalDrawDetailServiceDto> getWithdrawByCondition(@Param("page") Integer page, @Param("rows") Integer rows, @Param("userName") String userName, @Param("siteId") String siteId, @Param("drawStatus") String drawStatus) {
        List<CapitalDrawDetailServiceDto> listDto = null;
        List<CapitalDrawDetail> capitalDrawDetailList = capitalMapper.getWithdrawByCondition(page,rows,userName,siteId,drawStatus);
        if(capitalDrawDetailList != null && capitalDrawDetailList.size() > 0){
            listDto = new ArrayList<>();
            CapitalDrawDetailServiceDto dto = null;
            for (CapitalDrawDetail capitalDrawDetail:capitalDrawDetailList){
                dto = new CapitalDrawDetailServiceDto();
                BeanUtils.copyProperties(capitalDrawDetail, dto);
                listDto.add(dto);
            }
        }
        return listDto;
    }
}
