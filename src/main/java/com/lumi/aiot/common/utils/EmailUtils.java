package com.lumi.aiot.common.utils;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.regex.Pattern;




/**
 *
 * 邮箱 验证工具
 * 〈功能详细描述〉
 * @author evans
 * @version 2016年7月5日
 * @see EmailUtils
 * @since
 */
public class EmailUtils {

    /**
     * 网易邮箱
     */
    private static Set<String> NETEASE = Sets.newHashSet("163.com", "vip.163.com", "126.com", "vip.126.com", "yeah.net");

    /**
     * qq邮箱
     */
    private static Set<String> TENCENT = Sets.newHashSet("qq.com", "vip.qq.com");

    /**
     * gmail
     */
    private static Set<String> GOOGLE = Sets.newHashSet("gmail.com");

    /**
     * 新浪
     */
    private static Set<String> SINA = Sets.newHashSet("sina.com", "sina.cn", "vip.sina.com");

    /**
     * 移动139
     */
    private static Set<String> CMCC = Sets.newHashSet("139.com");

    /**
     * 搜狐
     */
    private static Set<String> SOHU = Sets.newHashSet("sohu.com");

    /**
     * foxmail
     */
    private static Set<String> FOXMAIL = Sets.newHashSet("foxmail.com");

    /**
     * iCloud
     */
    private static Set<String> ICLOUD = Sets.newHashSet("icloud.com");

    /**
     * 雅虎
     */
    private static Set<String> YAHOO = Sets.newHashSet("yahoo.com");

    /**
     * microsoft
     */
    private static Set<String> MICROSOFT = Sets.newHashSet("outlook.com", "hotmail.com");

    /**
     * 内部
     */
    private static Set<String> INNER = Sets.newHashSet("fenqile.com", "juzilicai.com");

    private static Set<String> ALL_NOLMARL_EMAIL = Sets.newHashSet();

    /**
     * 邮箱正则
     */
    public static final String EMAIL_REGEX = "^(\\w)+(\\.\\w+)*(\\-\\w+)*@(\\w)+((\\.\\w+)+)$";

    static {
        ALL_NOLMARL_EMAIL.addAll(NETEASE);
        ALL_NOLMARL_EMAIL.addAll(TENCENT);
        ALL_NOLMARL_EMAIL.addAll(GOOGLE);
        ALL_NOLMARL_EMAIL.addAll(SINA);
        ALL_NOLMARL_EMAIL.addAll(CMCC);
        ALL_NOLMARL_EMAIL.addAll(SOHU);
        ALL_NOLMARL_EMAIL.addAll(FOXMAIL);
        ALL_NOLMARL_EMAIL.addAll(ICLOUD);
        ALL_NOLMARL_EMAIL.addAll(YAHOO);
        ALL_NOLMARL_EMAIL.addAll(MICROSOFT);
        ALL_NOLMARL_EMAIL.addAll(INNER);
    }

    /**
     * 检查邮箱是否合法
     *
     * @param email 要验证的邮箱名
     * @return true | false
     */
    public static boolean checkEmailValid(String email) {
        String loewEmail = email.toLowerCase();
        return Pattern.matches(EMAIL_REGEX, loewEmail);
    }


}
