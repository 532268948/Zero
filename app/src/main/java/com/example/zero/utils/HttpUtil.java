package com.example.zero.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/27
 * 时 间： 15:40
 * 项 目： Zero
 */

public class HttpUtil {

    private static String IP="http://192.168.1.103:8080/Zero/";

    /**
     * 异步请求
     * @param address
     * @param callback
     */
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2,TimeUnit.SECONDS)
                .build();
        Request request=new Request.Builder().url(IP+address).build();
        client.newCall(request).enqueue(callback);
    }
    public static Response sendOkHttpRequest(int connectTime,int readTime,String address) throws IOException {
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(connectTime, TimeUnit.SECONDS)
                .readTimeout(readTime,TimeUnit.SECONDS)
                .build();
        Request request=new Request.Builder().url(IP+address).build();
        return (client.newCall(request)).execute();
    }

}
