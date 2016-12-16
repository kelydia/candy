package com.cjgod.candy.morning.controller;

import com.cjgod.candy.model.ComboBoxResult;
import com.cjgod.candy.model.PageResult;
import com.cjgod.candy.morning.common.annotation.CheckSessionTimeout;
import com.cjgod.candy.morning.common.annotation.RequiredInterceptor;
import com.cjgod.candy.service.dto.CapitalDrawDetailServiceDto;
import com.cjgod.candy.service.interfaces.CapitalService;
import com.cjgod.candy.service.interfaces.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
@CheckSessionTimeout
@Controller
@RequestMapping(value = "/capital")
public class CapitalController {
    @Autowired
    private EnumService enumService;
    @Autowired
    private CapitalService capitalService;

    /**
     * 进入提现明细列表
     *
     * @return
     */
    @RequiredInterceptor(required = true, desc = "进入提现明细列表")
    @RequestMapping(value = "/withdraw_list", method = {RequestMethod.GET})
    public String withdrawList() {
        return "capital/withdraw_list";
    }

    @RequiredInterceptor(required = true, desc = "查询提现状态")
    @RequestMapping(value = "/status", method = {RequestMethod.GET})
    @ResponseBody
    public List<ComboBoxResult> getWithdrawStatus() {
        List<ComboBoxResult> list = enumService.findWithdrawStatus();
        ComboBoxResult comboBoxResult = new ComboBoxResult();
        comboBoxResult.setLabel("全部");
        comboBoxResult.setValue("ALL");
        list.add(0,comboBoxResult);
        return list;
    }

    @RequiredInterceptor(required = true, desc = "提现明细列表分页查询")
    @RequestMapping(value = "/withdraw_pages", method = {RequestMethod.GET})
    @ResponseBody
    public Object queryWithdrawPages(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "siteId", required = false) String siteId, @RequestParam(value = "drawStatus") String drawStatus) {
        PageResult pageResult = new PageResult();
        List<CapitalDrawDetailServiceDto> list = capitalService.getWithdrawByCondition(page,rows,userName,siteId,drawStatus);
        pageResult.setRows(list);
        pageResult.setTotal(capitalService.getWithdrawPageCounts(userName,siteId,drawStatus));
        return pageResult;
    }



}
