package com.cjgod.candy.service.interfaces;

import com.cjgod.candy.model.ComboBoxResult;

import java.util.List;

/**
 * 获取状态枚举键值
 * Created by pei.li on 2016/11/9.
 */
public interface EnumService {
    /**
     * 获取所有角色
     *
     * @return
     */
    List<ComboBoxResult> findRole();

    /**
     * 查询彩种类型
     *
     * @return
     */
    List<ComboBoxResult> findLotteryType();

    /**
     * 查询彩种大类
     *
     * @return
     */
    List<ComboBoxResult> findLotteryClass();

    /**
     * 查询收支明细类型
     * @return
     */
    List<ComboBoxResult> findIAEType();

    /**
     * 查询取款状态
     * @return
     */
    List<ComboBoxResult> findWithdrawStatus();
}
