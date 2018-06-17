package com.lumi.aiot.common.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.regex.Pattern;

/**
 * 手机号码工具类
 *
 * @author Paty
 * @version 2016年7月15日
 * @see MobileUtils
 */
public class MobileUtils
{

    /**
     * 虚拟手机号码前缀
     */
    private static final String VIR_MOBILE_PREFIX = "9";

    /**
     * 手机号码正则
     */
    private static final String MOBILE_REGEX = "^(10[0-9]|13[0-9]|15[012356789]|17[0123456789]|18[0-9]|14[57])[0-9]{8}$";

    /**
     * 手机号码正则，包括虚拟账号
     */
    private static final String MOBILE_WITH_VIR_REGEX = "^9[0-9]{10}$";

    /**
     * 自动生成虚拟手机号
     *
     * @return 虚拟手机号
     */
    public static String getVirtureMobileNo()
    {
        long randPrefix = RandomUtils.nextLong(1000000000L, 10000000000L);
        return VIR_MOBILE_PREFIX + randPrefix;
    }

    /**
     * 验证是否虚拟手机
     * 只需要通过正则表达式校验，无需查询test_user db
     *
     * @param mobileNo
     * @return true | false
     */
    public static boolean isVirtureMobile(String mobileNo)
    {
        if (isMobile(mobileNo))
        {
            return false;
        } else if (isVirMobile(mobileNo))
        {
            return true;
        } else
        {
            throw new IllegalArgumentException("输入手机号:" + mobileNo + "不合法");//抛出公共的异常，java标准异常
        }
    }

    /**
     * 是否符合手机号规则
     *
     * @param mobileNo
     */
    public static boolean isMobile(String mobileNo)
    {
        return Pattern.matches(MOBILE_REGEX, mobileNo);
    }

    /**
     * 是否符合虚拟手机号规则
     *
     * @param mobileNo
     * @return true | false
     */
    public static boolean isVirMobile(String mobileNo)
    {
        return Pattern.matches(MOBILE_WITH_VIR_REGEX, mobileNo);
    }

    /**
     * 是否是合法手机号，包含虚拟手机号
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidMobilWithVir(String mobileNo)
    {
        if (isMobile(mobileNo) || isVirMobile(mobileNo))
        {
            return true;
        } else
        {
            return false;
        }
    }
}
