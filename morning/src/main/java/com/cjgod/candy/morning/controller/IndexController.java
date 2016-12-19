package com.cjgod.candy.morning.controller;

import com.cjgod.candy.model.JsonResult;
import com.cjgod.candy.morning.common.annotation.CheckSessionTimeout;
import com.cjgod.candy.morning.common.annotation.RequiredInterceptor;
import com.cjgod.candy.morning.common.auth.UserInfoCommon;
import com.cjgod.candy.morning.common.constant.Constant;
import com.cjgod.candy.morning.common.util.MapperUtil;
import com.cjgod.candy.morning.common.web.WebContext;
import com.cjgod.candy.service.dto.SystemAdminServiceDto;
import com.cjgod.candy.service.dto.SystemMenuServiceDto;
import com.cjgod.candy.service.interfaces.IndexService;
import com.cjgod.candy.service.interfaces.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 登录控制和主页操作Controller
 * create:pei.li
 * date:2016-9-23
 */
@Controller
public class IndexController {

    private SystemService systemService;
    private IndexService indexService;


    @Autowired
    public IndexController(SystemService systemService, IndexService indexService) {
        this.systemService = systemService;
        this.indexService = indexService;
    }


    /**
     * name:访问登录页面
     * explain:根据缓存的会话，查询是否具有登陆用户信息，如果有则返回主页，没有则进入登录页面
     *
     * @return 返回登录页面或进入主页
     */

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginPage() throws IOException {
        HttpServletRequest request = WebContext.getRequest();
        if(request == null) return "login";
        Object userObject = request.getSession(false).getAttribute(UserInfoCommon.PLATFORM_UID_KEY_NAME);
        if(userObject == null) return "login";
        return "redirect:/home";

    }

    /**
     * name:请求登录操作
     * explain:操作处理完成会将处理结果(jsonResult)返回,ret中（true代表处理成功，false代表处理失败，并附带msg错误信息）
     *
     * @param username 会员名称
     * @param password 密码
     * @return jsonResult
     */
    @RequiredInterceptor(required = true, desc = "登录")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession) {
        SystemAdminServiceDto systemAdminServiceDto = indexService.login(username, password);
        // 用户身份认证通过后，将用户信息存入 HttpSession 中供其他地方调用
        httpSession.setAttribute(UserInfoCommon.PLATFORM_UID_KEY_NAME, systemAdminServiceDto.getId());
        return new JsonResult();
    }

    /**
     * name:进入主页
     * explain:会根据会员的角色信息将角色菜单进行传递
     *
     * @return 进入主页
     */
    @RequiredInterceptor(required = true, desc = "进入主页")
    @CheckSessionTimeout
    @RequestMapping(value = "/home", method = {RequestMethod.GET})
    public String home(ModelMap model) throws IOException {
        SystemAdminServiceDto systemAdminServiceDto = systemService.findAdmin(UserInfoCommon.getPlatformUid());
        model.addAttribute(Constant.USER_NAME, systemAdminServiceDto.getUsername());
        return "home";
    }

    @RequiredInterceptor(required = true, desc = "获取菜单")
    @CheckSessionTimeout
    @RequestMapping(value = "/tree", method = {RequestMethod.POST})
    @ResponseBody
    public Object tree() {
        List<SystemMenuServiceDto> menuList = indexService.getMenu(UserInfoCommon.getPlatformUid());
        if (menuList != null) {
            return menuList;
        }
        return "";
    }

    /**
     * name:注销操作
     * explain:将会员的登录会话进行注销操作，并进入登录页面
     *
     * @return 返回登录页面
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(UserInfoCommon.PLATFORM_UID_KEY_NAME);
        return "login";
    }


    /**
     * name:登陆会员进行密码修改操作
     * explain:
     * 1.将登录会员的密码进行修改操作，
     * 2.操作处理完成会将处理结果(jsonResult)返回,ret中（true代表处理成功，false代表处理失败，并附带msg错误信息）
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return JsonResult
     */
    @RequiredInterceptor(required = true, desc = "修改密码")
    @CheckSessionTimeout //超时
    @RequestMapping(value = "/update-password", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) throws IOException {
        indexService.updatePassword(UserInfoCommon.getPlatformUid(), oldPassword, newPassword);
        return new JsonResult();
    }
}
