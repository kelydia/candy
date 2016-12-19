package com.cjgod.candy.common.util;

/**
 * Created by lichunjiang on 2016/12/19.
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Md5Util {
    private static Logger      log         = LoggerFactory.getLogger(Md5Util.class);
    private static final char  hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };
    private static final int   NUM_0XF     = 0xf;
    private static final int   NUM_16      = 16;
    private static final int   NUM_4       = 4;
    public static final byte[] BYTE_KEY    = { 7, 7, 7 };

    private Md5Util() {

    }

    public static String bytes2Md5(byte[] source) {
        String s = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[NUM_16 * 2];
            int k = 0;
            for (int i = 0; i < NUM_16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> NUM_4 & NUM_0XF];

                str[k++] = hexDigits[byte0 & NUM_0XF];
            }
            s = new String(str);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return s;
    }

    public static String string2Md5(String str) {
        String s = null;
        if (str != null) {
            try {
                s = bytes2Md5(str.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
        }
        return s;
    }

    public static String string2Md5(String str, String md5Salt) {
        return string2Md5(str + md5Salt);
    }

    public static String string2Md5(String str, String md5Salt, byte[] key) {
        if (str == null) {
            return str;
        }
        try {
            str += md5Salt;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bs = md.digest(str.getBytes("UTF-8"));
            byte[] dest = new byte[bs.length + BYTE_KEY.length];
            System.arraycopy(bs, 0, dest, 0, bs.length);
            System.arraycopy(BYTE_KEY, 0, dest, bs.length, BYTE_KEY.length);
            str = Base64.encodeBase64URLSafeString(dest);
        } catch (Exception e) {
            str = null;
        }
        return str;
    }
}
