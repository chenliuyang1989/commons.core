package com.lumi.aiot.common.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.core.encoder.ByteArrayUtil;

public class EncryptionUtils {

    /**
     * 身份证加密密钥
     */
    private static final String ID_KEY = "3746e6f63659376d68ee72f607eaf17b";

    /**
     * 身份证加密向量
     */
    private static final String ID_IV = "2bbe9f2c2027f0c41611401f69d95c83";

    /**
     * 银行卡号加密密钥
     */
    private static final String CARD_NO_KEY = "6499b9701895eece0e1f466c4af5de36";

    /**
     * 银行卡号加密向量
     */
    private static final String CARD_NO_IV = "9d02abcc8f6e095cea257e657cc5e53f";

    /**
     * 手机号加密密钥
     */
    private static final String MOBILE_KEY = "165c11a250b6197bf46e0ee6ed3cdced";

    /**
     * 手机号加密向量
     */
    private static final String MOBILE_IV = "d5d44aa3eaa33ee0ef3b0cf2c1bf9bc9";

    /**
     * 登陆密码盐值秘钥
     */
    private static final String PASSWD_SALT_KEY = "ed1d0d979e263532832a1608a9f36344";

    /**
     * 登陆密码盐值向量
     */
    private static final String PASSWD_SALT_IV = "2cbe79b2d98fae71530e79535b1fd1a1";

    /**
     * 支付密码盐值秘钥
     */
    private static final String PAY_PWD_SALT_KEY = "83f733c2cfc58f700a0bd22dc8bc3613";

    /**
     * 支付密码盐值向量
     */
    private static final String PAY_PWD_SALT_IV = "6e7c21ddfdf4582812b5dc0f895559dd";

    /**
     * admin查询用户信息秘钥
     */
    private static final String ADMIN_INFO_KEY = "177ce449d6676a316fa45ae0c1d09f63";

    /**
     * admin查询用户信息向量
     */
    private static final String ADMIN_INFO_IV = "8c427d4656aa4742d3e0ceb569d736df";

    /**
     * LOG
     */
    private static final Logger LOG = LoggerFactory.getLogger(EncryptionUtils.class);

    //URL加密密钥
    private static final String URL_KEY = "215f4969b253a4b91338fba4a01bf3cf";
    //URL加密向量
    private static final String URL_IV = "fab8d13df4c79778430f333a6532223c";

    /**
     * 对身份证号加密
     * 
     * @param creditId
     *            身份证号码
     * @return 加密后的身份证号码
     */
    public static String creditIdEncrypt(String creditId) {
        creditId = creditId.toUpperCase();
        return encrypt(creditId, ID_KEY, ID_IV);
    }

    /**
     * 对身份证号进行解密
     * 
     * @param enCreditId
     *            加密后的身份证号码
     * @return 解密后的身份证号码
     */
    public static String creditIdDecrypt(String enCreditId) {
        return decrypt(enCreditId, ID_KEY, ID_IV);
    }

    /**
     * AES 加密银行号
     * 
     * @param cardNo
     *            银行号
     * @return 加密后的银行号码
     */
    public static String cardNoEncrypt(String cardNo) {
        return encrypt(cardNo, CARD_NO_KEY, CARD_NO_IV);
    }

    /**
     * AES 解密银行号
     * 
     * @param enCardNo
     *            加密后的银行卡号
     * @return 解密后的银行号码
     */
    public static String cardNoDecrypt(String enCardNo) {
        return decrypt(enCardNo, CARD_NO_KEY, CARD_NO_IV);
    }

    /**
     * 加密手机号码
     * 
     * @param mobile
     *            手机号码
     * @return 加密后的手机号码
     */
    public static String mobileEncrypt(String mobile) {
        return encrypt(mobile, MOBILE_KEY, MOBILE_IV);
    }

    /**
     * 解密手机号码
     * 
     * @param enMobile
     *            加密后的手机号码
     * @return 解密后的手机号码
     */
    public static String mobileDecrypt(String enMobile) {
        return decrypt(enMobile, MOBILE_KEY, MOBILE_IV);
    }

    /**
     * 加密登陆密码盐值
     * 
     * @param passwdSalt
     *            登陆密码盐值
     * @return 加密后的登陆密码盐值
     */
    public static String passwdSaltEncrypt(String passwdSalt) {
        return encrypt(passwdSalt, PASSWD_SALT_KEY, PASSWD_SALT_IV);
    }

    /**
     * 解密登陆密码盐值
     * 
     * @param enPasswdSalt
     *            已经加密的登陆密码盐值
     * @return 解密后的登陆密码盐值
     */
    public static String passwdSaltDecrypt(String enPasswdSalt) {
        return decrypt(enPasswdSalt, PASSWD_SALT_KEY, PASSWD_SALT_IV);
    }

    /**
     * 加密交易密码盐值
     * 
     * @param payPwdSalt
     *            交易密码盐值
     * @return 加密后的交易密码盐值
     */
    public static String payPwdSaltEncrypt(String payPwdSalt) {
        return encrypt(payPwdSalt, PAY_PWD_SALT_KEY, PAY_PWD_SALT_IV);
    }

    /**
     * 解密交易密码盐值
     * 
     * @param enPayPwdSalt
     *            已经加密的交易密码盐值
     * @return 解密后的嫁衣密码盐值
     */
    public static String payPwdSaltDecrypt(String enPayPwdSalt) {
        return decrypt(enPayPwdSalt, PAY_PWD_SALT_KEY, PAY_PWD_SALT_IV);
    }

    /**
     * admin_itg查询用户信息对外加密
     * 
     * @param adminInfo
     *            admin用户信息
     * @return 加密后的admin用户信息
     */
    public static String adminInfoEncrypt(String adminInfo) {
        return encrypt(adminInfo, ADMIN_INFO_KEY, ADMIN_INFO_IV);
    }

    /**
     * admin_itg查询用户信息对外解密密
     * 
     * @param enAdminInfo
     *            已经加密的用户信息
     * @return 解密后的admin用户信息
     */
    public static String adminInfoDecrypt(String enAdminInfo) {
        return decrypt(enAdminInfo, ADMIN_INFO_KEY, ADMIN_INFO_IV);
    }

    /**
     * 目前只有fastdfs加密使用,估KEY和IV是之前fastdfs的,
     * 且没有做urlencode
     * @param url
     * @return
     */
    public static String urlEncrypt(String url){
        return encrypt(url, URL_KEY, URL_IV);
    }

    /**
     * 目前只有fastdfs加密使用,估KEY和IV是之前fastdfs的,
     *
     * @param url
     * @return
     */
    public static String urlDecrypt(String url){
        return decrypt(url, URL_KEY, URL_IV);
    }
    /**
     * AES加密器
     * 
     * @param content
     *            需要加密的内容
     * @param key
     *            加密 key
     * @param iv
     *            加密向量
     * @return 加密后的文本
     */
    public static String encrypt(String content, String key, String iv) {
        byte[] crypted = null;
        try {
            Cipher cipher = getAESCipher(Cipher.ENCRYPT_MODE, key, iv);
            crypted = cipher.doFinal(content.getBytes("UTF-8"));
        }
        catch (Throwable e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return StringUtils.newString(Base64.encodeBase64(crypted), "UTF-8");
    }

    /**
     * AES 解密
     * 
     * @param content
     *            需要解密的内容
     * @param key
     *            key
     * @param iv
     *            iv
     * @return 解密后的文本
     */
    public static String decrypt(String content, String key, String iv) {
        byte[] decryptContent = null;
        try {
            Cipher cipher = getAESCipher(Cipher.DECRYPT_MODE, key, iv);
            decryptContent = cipher.doFinal(Base64.decodeBase64(content));
        }
        catch (Throwable e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return StringUtils.newString(decryptContent, "UTF-8");
    }

    /**
     * 获得AESClipher
     * 
     *
     * @param mode mode 0 | 1 (Cipher.ENCRYPT_MODE, Cipher.DECRYPT_MODE)
     * @param key key
     * @param iv iv
     * @throws NoSuchPaddingException
     *             NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     *             NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     *             InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     *             InvalidKeyException
     * @return Clipher 
     */
    private static Cipher getAESCipher(int mode, String key, String iv)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(ByteArrayUtil.hexStringToByteArray(key),
            "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ByteArrayUtil.hexStringToByteArray(iv));
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKeySpec, ivSpec);
        return cipher;
    }

}
