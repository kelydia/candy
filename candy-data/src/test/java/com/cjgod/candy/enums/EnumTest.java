package com.cjgod.candy.enums;

import org.junit.Test;

/**
 * Created by Administrator on 2016/12/16.
 */
public class EnumTest {

    @Test
    public void IAETypeEnumTest(){
        IAETypeEnum iaeTypeEnum = IAETypeEnum.fromName("REFUND");
        System.out.println("name: "+iaeTypeEnum.name()+" desc: "+iaeTypeEnum.getDesc() + " value: "+iaeTypeEnum.getValue());
    }

    @Test
    public void WithdrawStatusEnumTest(){
        WithdrawStatusEnum withdrawStatusEnum = WithdrawStatusEnum.fromName("withdraw_success");
        System.out.println("name: "+withdrawStatusEnum.name()+" desc: "+withdrawStatusEnum.getDesc());
    }

}
