package com.cjgod.candy.dao.db.interfaces;

import com.cjgod.candy.dao.db.interfaces.db.dto.CapitalDrawDetail;
import com.cjgod.candy.dao.db.interfaces.db.interfaces.CapitalMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
@Service
public class CapitalService implements ICapitalService{
    @Autowired
    private CapitalMapper mapper;

    @Override
    public Integer getWithdrawPageCounts(@Param("userName") String userName, @Param("siteId") String siteId, @Param("drawStatus") String drawStatus) {
        return mapper.getWithdrawPageCounts(userName,siteId,drawStatus);
    }

    @Override
    public List<CapitalDrawDetail> getWithdrawByCondition(@Param("page") Integer page, @Param("rows") Integer rows, @Param("userName") String userName, @Param("siteId") String siteId, @Param("drawStatus") String drawStatus) {
        return null;
    }

}
