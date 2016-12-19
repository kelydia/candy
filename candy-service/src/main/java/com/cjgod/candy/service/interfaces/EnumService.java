package com.cjgod.candy.service.interfaces;

import com.cjgod.candy.model.ComboBoxResult;

import java.util.List;

/**
 * 获取状态枚举键值
 * Created by pei.li on 2016/11/9.
 */
public interface EnumService {

    /**
     * 查询取款状态
     * @return
     */
    List<ComboBoxResult> findWithdrawStatus();
}
