package com.boxuegu.utils;

/**
 * Created by Tao on 2020/3/31.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by apple on 18/3/20.
 */

public class MD5Utils {
    public static String md5(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result){
                int number = b&0xff;
                String hex = Integer.toHexString(number);
                if (hex.length()==1){
                    sb.append("0"+hex);
                }else{
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}

