package com.cjgod.candy.service.impl;

import com.cjgod.candy.enums.WithdrawStatusEnum;
import com.cjgod.candy.model.ComboBoxResult;
import com.cjgod.candy.service.interfaces.EnumService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichunjiang on 2016/11/9.
 */
@Service
public class EnumServiceImpl implements EnumService {

    @Override
    public List<ComboBoxResult> findWithdrawStatus() {
        List<ComboBoxResult> comboBoxResults = new ArrayList<>();
        for (WithdrawStatusEnum withdrawStatusEnum : WithdrawStatusEnum.values()) {
            ComboBoxResult comboBoxResult = new ComboBoxResult();
            comboBoxResult.setValue(withdrawStatusEnum.name());
            comboBoxResult.setLabel(withdrawStatusEnum.getDesc());
            comboBoxResults.add(comboBoxResult);
        }
        return comboBoxResults;
    }


}
