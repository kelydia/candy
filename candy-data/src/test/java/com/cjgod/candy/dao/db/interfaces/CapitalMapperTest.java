package com.cjgod.candy.dao.db.interfaces;

import com.cjgod.candy.common.AbstractJUnit;
import com.cjgod.candy.logger.InfoLoggerUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/12/15.
 */
public class CapitalMapperTest extends AbstractJUnit{
    @Autowired
    private CapitalMapper mapper;

    @Test
    public void test1(){
        InfoLoggerUtil.w("hello");
        int count = 0;
        count = mapper.getWithdrawPageCounts("","","ALL");
        System.out.println("查询到记录条数： "+count);
    }
}
