package com.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

public class AESUtil {

    /**
     * 密钥算法
     * java6支持56位密钥，bouncycastle支持64位
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法/工作模式/填充方式
     * <p>
     * JAVA6 支持PKCS5PADDING填充方式
     * Bouncy castle支持PKCS7Padding填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    /**
     * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
     *
     * @return byte[] 二进制密钥
     */
    public static byte[] initkey() throws Exception {

        //实例化密钥生成器
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
////	        //初始化密钥生成器，AES要求密钥长度为128位、192位、256位
////	        kg.init(256);
//        kg.init(128);
////	        //生成密钥
//        SecretKey secretKey = kg.generateKey();
////	        //获取二进制密钥编码形式
//        return secretKey.getEncoded();
        //为了便于测试，这里我把key写死了，如果大家需要自动生成，可用上面注释掉的代码
        return new byte[]{0x2a, 0x6, 0x49, 0x23, (byte) 0xf1, 0x74, 0x14,
                (byte) 0x99, (byte) 0xb4, (byte) 0xe4, (byte) 0xcc, (byte) 0xc7,
                (byte) 0xe0, (byte) 0xf8, (byte) 0xec, 0x6e};
    }

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     */
    public static Key toKey(byte[] key) throws Exception {
        //实例化DES密钥
        //生成密钥
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密后的数据
     */
    /*public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        //还原密钥
        Key k = toKey(key);
        *//**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         *//*
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        //初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        //执行操作
        return cipher.doFinal(data);
    }*/

    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密后的数据
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        //欢迎密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * @param args
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String str = "郭世杰";
        byte[] key = AESUtil.initkey();
        System.out.println(key);
        System.out.println("原文：" + str.getBytes());

//        //初始化密钥
//        byte[] key;
//        try {
//            key = AESUtil.initkey();
//            System.out.print("密钥：");
//            for (int i = 0; i < key.length; i++) {
//                System.out.printf("%#x ", key[i]);
//            }
//            System.out.print("\n");
////            System.out.println(new String(key, "gbk"));
//
//            //加密数据
//            byte[] data = AESUtil.encrypt(str.getBytes(), key);
////            System.out.print("加密后：");
////            for (int i = 0; i < data.length; i++) {
////                byte bb = data[i];
////                System.out.printf("%x", bb);
////            }
////            System.out.print("\n");
////            String base64String = new String(Base64.encodeBase64(data));
////            System.out.println(        } catch (Exception e) {
////            e.printStackTrace();
////        }"加密后base64：" + base64String);
//
//            String base64String = "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";
//
//            //解密数据
//            data = AESUtil.decrypt(Base64.decodeBase64(base64String.getBytes()), key);
//            System.out.println("解密后：" + new String(data));
//

    }
}
