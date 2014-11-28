package com.jshuabo.frame.server.util.rsa;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Cipher;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;


/**
 * @ClassName: RSAKeyUtil
 * @Description: RSAKeyUtil 
 * @author: lianghe.yuan
 * @date: Mar 2, 2014 5:42:52 AM
 */
public class RSAKeyUtil {
    private static final String defaultModulus = "136545653951974512462273724152700453979334309820637127160540903898617776219647094972301445543975834712378306889030410702693772704009614768803459506523418231668568608649671452065310549529343291843701045352852261157399296322816635099218576547295424643647474006462300912236267522042505928811002257656262005480857";
    private static final String defaultPrivateExponet = "87223206082754184502199172802060695567249821418910732737978308448841640350631032606320516902711511732288713850227644134726522429782542879307689225645000226809379487050048260007625336312015384696360202543318757399866270243884184410247013858684651130393760275258685048261523272064221040220263630552381211679745";
    private static final String defaultPublicExponent = "65537";
    
    
    public static PublicKey getPublicKey(String modulus, String publicExponent) throws Exception {
        BigInteger m = new BigInteger(modulus);
        BigInteger e = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String modulus, String privateExponent) throws Exception {
        BigInteger m = new BigInteger(modulus);
        BigInteger e = new BigInteger(privateExponent);
        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
    
    public static String encrypt(String msg, String modulus, String publicExponent) throws Exception {
        if (StringUtils.isEmpty(modulus) || StringUtils.isEmpty(publicExponent)) {
            modulus = RSAKeyUtil.defaultModulus;
            publicExponent = RSAKeyUtil.defaultPublicExponent;
        }
        PublicKey publicKey = getPublicKey(modulus, publicExponent);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        byte[] msgs = msg.getBytes("UTF-8");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = msgs.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(msgs, offSet, 117);
            } else {
                cache = cipher.doFinal(msgs, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(encryptedData, "ISO-8859-1");
    }
    
    public static String decrypt(String msg, String modulus, String privateExponet) throws Exception {
        if (StringUtils.isEmpty(modulus) || StringUtils.isEmpty(privateExponet)) {
            modulus = RSAKeyUtil.defaultModulus;
            privateExponet = RSAKeyUtil.defaultPrivateExponet;
        }
        PrivateKey privateKey = getPrivateKey(modulus, privateExponet);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedData = msg.getBytes("ISO-8859-1");
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;

        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(encryptedData, offSet, 128);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 128;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, "UTF-8");
    }
    
    /**
     * @Title: encryptMapExcludeKeys
     * @Description: 
     *                      WARNS: key can be any type, but value will be encrypted as java.lang.String(using toString())
     *                      WARNS: return Map<Object, Object>    
     * @param map
     * @param excludeKeys
     * @param modulus
     * @param publicExponent
     * @return Map<Object, Object>
     * @throws Exception
     * @return: Map<Object,Object>
     */
    public static Map<Object, Object> encryptMapExcludeKeys(Map<?, ?> map, Object[] excludeKeys, String modulus, String publicExponent) throws Exception {
        Map<Object, Object> result = new HashMap<Object, Object>();
        Iterator<?> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (ArrayUtils.contains(excludeKeys, key)) {
                result.put(key, value);
            } else {
                if (value == null) value = StringUtils.EMPTY;
                result.put(key, encrypt(value.toString(), modulus, publicExponent));
            }
        }
        return result;
    }
    
}
