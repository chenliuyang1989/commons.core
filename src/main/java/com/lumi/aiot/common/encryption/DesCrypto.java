package com.lumi.aiot.common.encryption;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class DesCrypto {

    /**
     * 8-byte Salt
     */
    private static final byte[] SALT = {(byte)0xa9, (byte)0x9a, (byte)0xa8, (byte)0xd2,
            (byte)0x59, (byte)0x8c, (byte)0xc3, (byte)0x13};

    /**
     * Iteration count
     */
    private static final int ITERATIONCOUNT = 15;

    /**
     * @param mod
     *            mod
     * @param password
     *            password
     * @return Cipher
     * @throws InvalidKeySpecException
     *             key不正常
     * @throws NoSuchAlgorithmException
     *             不支持此算法
     * @throws NoSuchPaddingException
     *             NoSuchPadding
     * @throws InvalidKeyException
     *             key不正常
     * @throws InvalidAlgorithmParameterException
     *             参数不正常
     */
    private static Cipher getCipher(int mod, String password)
            throws InvalidKeySpecException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), SALT, ITERATIONCOUNT);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
        // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATIONCOUNT);
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(mod, key, paramSpec);
        return cipher;
    }

    /**
     * 加密方法
     *
     * @param str
     *            被加密串
     * @param password
     *            密钥
     * @return 加密后的串
     */
    public static String encrypt(String str, String password) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("utf-8");
            // Encrypt
            byte[] enc = getCipher(Cipher.ENCRYPT_MODE, password).doFinal(utf8);
            // Encode bytes to base64 to get a string
            return new String(Hex.encodeHex(enc));
        }
        catch (BadPaddingException e) {
            // ignore
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 解密方法
     *
     * @param str
     *            需要解密的串
     * @param password
     *            密钥
     * @return 解密后的串
     */
    public static String decrypt(String str, String password) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            // Decode base64 to get bytes
            byte[] dec = Hex.decodeHex(str.toCharArray());
            // Decrypt
            byte[] utf8 = getCipher(Cipher.DECRYPT_MODE, password).doFinal(dec);
            // Decode using utf-8
            return new String(utf8, "utf-8");
        }
        catch (BadPaddingException e) {
            // ignore
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
