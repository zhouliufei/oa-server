package com.fanfan.Util;

public class StringUtil {

    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "FANFAN_";

    /**
     * 角色前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * 请求的uri
     */
    public static final String URI_PREFIX = "URI_";

    /**
     *
     */
    public static boolean isEmpty(String str) {
        if(str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

}
