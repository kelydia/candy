package com.cjgod.candy.morning.common.auth;

/**
 * Created by lichunjiang on 2016/12/16.
 */

import com.cjgod.candy.common.exception.CandySessionTimeoutException;
import com.cjgod.candy.morning.common.util.MapperUtil;
import com.cjgod.candy.morning.common.web.WebContext;

public class UserInfoCommon {

    public static String PLATFORM_UID_KEY_NAME = "uid";

    /**
     * 从session中获取平台用户uid
     *
     * @return
     */
    public static Integer getPlatformUid() throws CandySessionTimeoutException {

        try {

            Integer uid = MapperUtil.convert(
                    WebContext.getRequest().getSession(false).getAttribute(PLATFORM_UID_KEY_NAME), Integer.class);

            if (uid != null)
                return uid;

        } catch (Exception e) {
        }

        throw new CandySessionTimeoutException();
    }
}
