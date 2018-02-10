package org.jypj.dev.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5Encrypt(String data) {
        String resultString = null;
        try {
            resultString = new String(data);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    private static String byte2hexString(byte[] bytes) {
        StringBuffer bf = new StringBuffer(bytes.length * 2);
        for (byte aByte : bytes) {
            if ((aByte & 0xff) < 0x10) {
                bf.append("0");
            }
            bf.append(Long.toString(aByte & 0xff, 16));
        }
        return bf.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = md5Encrypt("gzyx12345");
        System.out.println(str);
    }
}
