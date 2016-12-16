package com.cjgod.candy.service.interfaces;

import com.cjgod.candy.service.dto.CapitalDrawDetailServiceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 * 资金管理服务
 */
public interface CapitalService {

    /**
     * 根据用户名\站点\提现状态获取提现明细分页总页数
     * @param userName
     * @param siteId
     * @param drawStatus
     * @return
     */
    Integer getWithdrawPageCounts(@Param("userName") String userName, @Param("siteId") String siteId, @Param("drawStatus") String drawStatus);

    /**
     * 根据用户名\站点\提现状态获取提现明细信息
     * @param page
     * @param rows
     * @param userName
     * @param siteId
     * @param drawStatus
     * @return
     */
    List<CapitalDrawDetailServiceDto> getWithdrawByCondition(@Param("page") Integer page, @Param("rows") Integer rows, @Param("userName") String userName, @Param("siteId") String siteId, @Param("drawStatus") String drawStatus);
}
