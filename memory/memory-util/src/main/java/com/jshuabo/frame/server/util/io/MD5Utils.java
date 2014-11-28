/**
 * Copyright©2014 www.jshuabo.com. all rights reserved.
 * 
 * @Title: MD5Utils.java
 * @Prject: memory-util
 * @Package: com.jshuabo.frame.server.util.io
 * @author: peng.wu
 * @date: 2014年4月22日 下午4:19:48
 * @version:
 * @Description:
 */
package com.jshuabo.frame.server.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: MD5Utils
 * @Description:
 * @author: peng.wu
 * @date: 2014年4月22日 下午4:19:48
 */
public class MD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    private static int bufferSize = 256 * 1024;
    /**
     * Per thread MD5 instance.
     */
    private static final ThreadLocal<MessageDigest> perThreadMd5 =
            new ThreadLocal<MessageDigest>() {
                @Override
                protected MessageDigest initialValue() {
                    try {
                        return MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        logger.error("MD5 implementation not found : {}", e.getLocalizedMessage());
                        throw new RuntimeException("MD5 implementation not found", e);
                    }
                };
            };

    /**
     * Generate MD5 digest.
     * 
     * @param input input data to be hashed.
     * @return MD5 digest.
     */
    public static String stringMD5(String str) {
        MessageDigest messageDigest = perThreadMd5.get();

        messageDigest.reset();
        messageDigest.update(str.getBytes());

        byte[] resultByteArray = messageDigest.digest();
        return byteArrayToHex(resultByteArray);
    }

    public static String fileMD5(String filePath) {
        // 缓冲区大小（这个可以抽出一个参数）
        FileInputStream fileInputStream = null;
        DigestInputStream digestInputStream = null;
        try {
            // 拿到一个MD5转换器（同样，这里可以换成SHA1）
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // 使用DigestInputStream
            fileInputStream = new FileInputStream(filePath);
            digestInputStream = new DigestInputStream(fileInputStream, messageDigest);

            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0);

            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();

            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();

            // 同样，把字节数组转换成字符串
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            logger.error("catched NoSuchAlgorithmException : {}", e.getLocalizedMessage());
        } catch (FileNotFoundException e) {
            logger.error("catched FileNotFoundException : {}", e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("catched IOException : {}", e.getLocalizedMessage());
        } finally {
            try {
                digestInputStream.close();
            } catch (Exception e) {
                logger.warn("catched exception when digestInputStream.close() : {}", e.getLocalizedMessage());
            }
            try {
                fileInputStream.close();
            } catch (Exception e) {
                logger.warn("catched exception when fileInputStream.close() : {}", e.getLocalizedMessage());
            }
        }
        return null;
    }

    public static String fileMD5(File file) {
        return fileMD5(file.getPath());
    }

    public static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];

        int index = 0;
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    public static void main(String[] args) {
        String a = MD5Utils.stringMD5("test");
        System.out.println(a);

        a = MD5Utils.fileMD5("C:\\Users\\UESR\\Desktop\\aa");
        System.out.println(a);
    }
}
