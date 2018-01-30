package com.example.zero.utils;

import android.util.Base64;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/27
 * 时 间： 17:55
 * 项 目： Zero
 */

public class SecretUtil {

    public static String Encrypt(String string){
        return Base64.encodeToString(string.getBytes(),Base64.DEFAULT);
    }

    public static String Decrypt(String string){
        return new String(Base64.decode(string,Base64.DEFAULT));
    }
}
