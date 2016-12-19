package com.cjgod.candy.service;

import com.cjgod.candy.common.AbstractJUnit;
import com.cjgod.candy.service.interfaces.CapitalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lichunjiang on 2016/12/16.
 */
public class CapitalServiceTest extends AbstractJUnit{
    @Autowired
    private CapitalService capitalService;

    @Test
    public void test1(){
        //InfoLoggerUtil.e("出错啦");
        int count = capitalService.getWithdrawPageCounts("","","ALL");
        System.out.println("查询到记录条数： "+count);
    }

}
