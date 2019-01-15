package com.estartus.auth.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author aestartus
 * @since 12/29/18.
 */
public class Crypto {

    public static String sha256(byte[] base){
        return getSha256(base);
    }

    public static String sha256(String base){
        try {
            return getSha256(base.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String getSha256(byte[] base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base);
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
