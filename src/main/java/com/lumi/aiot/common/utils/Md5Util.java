package com.lumi.aiot.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);

    /**
     * 十六进制数常量
     */
    private static final char[] HEXDIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * utf8常量
     */
    public static final String CHARSET_UTF8 = "UTF-8";


    private static final String DIGEST_TYPE = "MD5";


    /**
     * 功能：加盐版的MD5.返回格式为MD5(密码+{盐值})
     *
     * @param password 密码
     * @param salt     盐值
     * @return String
     * @author cikyye
     * @date 2015年06月30日
     */
    public static String getMD5StringWithSalt(String password, String salt)
    {

        if (password == null)
        {
            throw new IllegalArgumentException("password不能为null");
        }
        if (salt == null || salt.equals(""))
        {
            throw new IllegalArgumentException("salt不能为空");
        }
        if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1))
        {
            throw new IllegalArgumentException("salt中不能包含 { 或者 }");
        }

        return getMD5String(password + "{" + salt.toString() + "}");
    }



    /**
     * 功能：得到一个字符串的MD5值。
     *
     * @param str 字符串
     * @return String
     * @author cikyye
     * @date 2015年06月30日
     */
    public static String getMD5String(String str)
    {
        try
        {
            return getMD5String(str.getBytes(CHARSET_UTF8));
        } catch (UnsupportedEncodingException e)
        {
            LOGGER.error("get bytes UnsupportedEncoding", e);
        }
        return null;
    }

    /**
     * 根据btyes获取md5串
     *
     * @param bytes 需md5的字节数组
     * @return md5串
     */
    private static String getMD5String(byte[] bytes)
    {
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance(DIGEST_TYPE);
        } catch (NoSuchAlgorithmException e)
        {
            LOGGER.error(e.getMessage());
        }
        messageDigest.update(bytes);
        return bufferToHex(messageDigest.digest());
    }

    /**
     * 将bytes转成Hex串
     *
     * @param bytes 需转换的字节数组
     * @return Hex串
     */
    private static String bufferToHex(byte[] bytes)
    {
        return bufferToHex(bytes, 0, bytes.length);
    }

    /**
     * 将bytes转成Hex串
     *
     * @param bytes 需转换的字节数组
     * @param m     开始下标
     * @param n     处理长度
     * @return Hex串
     */
    private static String bufferToHex(byte[] bytes, int m, int n)
    {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++)
        {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    /**
     * 将字节转顾Hex后加入stringbuffer后
     *
     * @param bt           增加的字节
     * @param stringbuffer stringbuffer
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer)
    {
        char c0 = HEXDIGITS[(bt & 0xf0) >> 4];
        char c1 = HEXDIGITS[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
