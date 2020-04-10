package com.gwideal.common.util;

import it.sauronsoftware.base64.Base64;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import javax.crypto.Cipher;
/**
 * RSA算法工具类
 */
public class RSAUtils {
  
      /**
       * 加密算法RSA
       */
      public static final String KEY_ALGORITHM = "RSA";
      
      /**
       * 签名算法
       */
      public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
  
      /**
       * 获取公钥的key
       */
      private static final String PUBLIC_KEY = "RSAPublicKey";
  
      /**
       * 获取私钥的key
       */
      private static final String PRIVATE_KEY = "RSAPrivateKey";
  
      /**
       * RSA最大加密明文大小
       */
      private static final int MAX_ENCRYPT_BLOCK = 117;
  
      /**
       * RSA最大解密密文大小
       */
      private static final int MAX_DECRYPT_BLOCK = 128;
      
      /**
       * 已经生成的私钥，公钥在登录页面进行加密
       */
      private static final String PRIVATEKEY="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALjPaHgm5pUkqWMLq10HT25xnNSNvIYKMlwdiLjWXKlQdYZnkzgig+Y5ZJtU71dlEFDOnfEHCtsXuUnErgQZaX2L4GO5F0JR/XT2MwSjqQxXKMlO1pYB+qiFxgE4hdncSqDLK3ixkIi8fgkAH/SaYE9DXBLqDBbwg9IkSjZbHcJ9AgMBAAECgYATPYyrzaj96AY2iJV7pvIvFmb0fhkdf1xE2NJOJAsbmYPSzV+3XWiMBHej8FDGsX/kWc55tHj93FdvBan0RU0FFsnTqtislRSkGU3Hk5pMENC5JEEZDWrjzSRiAG/GivV8cZDzrJD4wQpub24HWWP70sFeO6pi45+ophFhrjEUIQJBAN1KYXuxJ2WDVChMUQHVE7mgDtWEczkIQ4Ofb3VfLENPqYiSTe3G7vBPTY/QawuGyeMyvwaJ7Oe2DMK6kT3bCPkCQQDVzDVRKL5e57e//Fr1Kj4PmnCFxL+Zj6Igpct5nrSy59peH44i4HneEpyaNAsZb8f700te0F92dGNGsg92YUqlAkAMT50IwpxMe3ftHgHLtkz4oLzyiAHZBGqtIeuTfg36WK432NvZBJ46ZE8OVyv9YixNJX+XH4k8gtZG5xY5eLwRAkBCFHvmOdGdQ8FndXgnjobgC7v7nHQb4mkZ7iSV0pfHNCIqNVaZMQh5uwbsImnL0uDc1NHW3FClLBQDJcXhsPCBAkArpsWJmcvRvQG0TgbXQjZbDKaAv2P6z3vpQSeCch/d9CNsnsRtSfuhkzIAIczxdF/AHGDIw7sL/FR8K4C5/o/d";
  
      /**
       * 生成密钥对(公钥和私钥)
       * @return
       * @throws Exception
       */
      public static Map<String, Object> genKeyPair() throws Exception {
          KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
          keyPairGen.initialize(1024);
          KeyPair keyPair = keyPairGen.generateKeyPair();
          RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
          RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
          Map<String, Object> keyMap = new HashMap<String, Object>(2);
          keyMap.put(PUBLIC_KEY, publicKey);
          keyMap.put(PRIVATE_KEY, privateKey);
          return keyMap;
      }
 
      /**
      * 用私钥对信息生成数字签名
      * @param data 已加密数据
      * @param privateKey 私钥(BASE64编码)
      * @return
      * @throws Exception
      */
     public static String sign(byte[] data, String privateKey) throws Exception {
         byte[] keyBytes = Base64Utils.decode(privateKey);
         PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
         PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
         Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
         signature.initSign(privateK);
         signature.update(data);
         return Base64Utils.encode(signature.sign());
     }
 
     /**
      * 校验数字签名
      * @param data 已加密数据
      * @param publicKey 公钥(BASE64编码)
      * @param sign 数字签名
      * @return
      * @throws Exception
      * 
      */
     public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
         byte[] keyBytes = Base64Utils.decode(publicKey);
         X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
         PublicKey publicK = keyFactory.generatePublic(keySpec);
         Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
         signature.initVerify(publicK);
         signature.update(data);
         return signature.verify(Base64Utils.decode(sign));
     }
 
     /**
      * 私钥解密
      * @param encryptedData 已加密数据
      * @param privateKey 私钥(BASE64编码)
      * @return
      * @throws Exception
      */
     public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
         byte[] keyBytes = Base64Utils.decode(privateKey);
         PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
         Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
         Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
         cipher.init(Cipher.DECRYPT_MODE, privateK);
         int inputLen = encryptedData.length;
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         int offSet = 0;
         byte[] cache;
         int i = 0;
         // 对数据分段解密
         while (inputLen - offSet > 0) {
             if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                 cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
             } else {
                 cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
             }
             out.write(cache, 0, cache.length);
             i++;
             offSet = i * MAX_DECRYPT_BLOCK;
         }
         byte[] decryptedData = out.toByteArray();
         out.close();
         return decryptedData;
     }
 
     /**
      * 公钥解密
      * @param encryptedData  已加密数据
      * @param publicKey 公钥(BASE64编码)
      * @return
      * @throws Exception
      */
     public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
         byte[] keyBytes = Base64Utils.decode(publicKey);
         X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
         Key publicK = keyFactory.generatePublic(x509KeySpec);
         Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
         cipher.init(Cipher.DECRYPT_MODE, publicK);
         int inputLen = encryptedData.length;
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         int offSet = 0;
         byte[] cache;
         int i = 0;
         // 对数据分段解密
         while (inputLen - offSet > 0) {
             if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                 cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
             } else {
                 cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
             }
             out.write(cache, 0, cache.length);
             i++;
             offSet = i * MAX_DECRYPT_BLOCK;
         }
         byte[] decryptedData = out.toByteArray();
         out.close();
         return decryptedData;
     }
 
     /**
      * 公钥加密
      * @param data 源数据
      * @param publicKey 公钥(BASE64编码)
      * @return
      * @throws Exception
      */
     public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
         byte[] keyBytes = Base64Utils.decode(publicKey);
         X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
         Key publicK = keyFactory.generatePublic(x509KeySpec);
         // 对数据加密
         Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
         cipher.init(Cipher.ENCRYPT_MODE, publicK);
         int inputLen = data.length;
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         int offSet = 0;
         byte[] cache;
         int i = 0;
         // 对数据分段加密
         while (inputLen - offSet > 0) {
             if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                 cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
             } else {
                 cache = cipher.doFinal(data, offSet, inputLen - offSet);
             }
             out.write(cache, 0, cache.length);
             i++;
             offSet = i * MAX_ENCRYPT_BLOCK;
         }
         byte[] encryptedData = out.toByteArray();
         out.close();
         return encryptedData;
     }
 
     /**
      * 私钥加密
      * @param data 源数据
      * @param privateKey 私钥(BASE64编码)
      * @return
      * @throws Exception
      */
     public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
         byte[] keyBytes = Base64Utils.decode(privateKey);
         PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
         KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
         Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
         Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
         cipher.init(Cipher.ENCRYPT_MODE, privateK);
         int inputLen = data.length;
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         int offSet = 0;
         byte[] cache;
         int i = 0;
         // 对数据分段加密
         while (inputLen - offSet > 0) {
             if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                 cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
             } else {
                 cache = cipher.doFinal(data, offSet, inputLen - offSet);
             }
             out.write(cache, 0, cache.length);
             i++;
             offSet = i * MAX_ENCRYPT_BLOCK;
         }
         byte[] encryptedData = out.toByteArray();
         out.close();
         return encryptedData;
     }
 
     /**
      * 获取私钥
      * @param keyMap 密钥对
      * @return
      * @throws Exception
      */
     public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
         Key key = (Key) keyMap.get(PRIVATE_KEY);
         return new String(Base64.encode(key.getEncoded()));
     }
 
     /**
      * 获取公钥
      * @param keyMap 密钥对
      * @return
      * @throws Exception
      */
     public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
         Key key = (Key) keyMap.get(PUBLIC_KEY);
         return new String(Base64.encode(key.getEncoded()));
     }
 
     /**
      * 公钥加密
      * @param data 要加密的数据
      * @param PUBLICKEY 公钥
      * @return
      */
     public static String encrypt(String data, String PUBLICKEY) {
         try {
             return new String(Base64.encode(encryptByPublicKey(data.getBytes(), PUBLICKEY)));
         } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         return "";
     }
 
     /**
      * 私钥解密
      * @param data 要解密的数据
      * @param PRIVATEKEY 私钥
      * @return
      */
     public static String decrypt(String data, String PRIVATEKEY) {
         try {
             return new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(data), PRIVATEKEY),"UTF-8");//以utf-8的方式生成字符串
         } catch (Exception e) {
             e.printStackTrace();
         }
         return "";
     }
     
     /**
      * 私钥解密
      * 使用已生成的私钥，公钥在登录页面进行加密
      * @param data 要解密的数据
      * @return
      */
     public static String decrypt(String data) throws Exception{
           return new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(data),PRIVATEKEY),"UTF-8");//以utf-8的方式生成字符串
     }
}