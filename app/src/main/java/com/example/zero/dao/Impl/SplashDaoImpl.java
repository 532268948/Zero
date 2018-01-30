package com.example.zero.dao.Impl;

import android.content.Context;
import android.util.Log;

import com.example.zero.dao.SplashDao;
import com.example.zero.utils.CacheUtil;
import com.example.zero.utils.HttpUtil;
import com.example.zero.utils.SecretUtil;
import com.example.zero.utils.StringListener;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Response;


/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/27
 * 时 间： 12:54
 * 项 目： Zero
 */

public class SplashDaoImpl implements SplashDao {
    @Override
    public Boolean isRemember(Context context) {
        return CacheUtil.getBoolean(context,"isRemember");
    }

    @Override
    public Boolean Login(final Context context, final StringListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String zero= SecretUtil.Encrypt(CacheUtil.getString(context,"zero"));
                String password=SecretUtil.Encrypt(CacheUtil.getString(context,"password"));
                String responseData=null;
                try {
                    Response response = HttpUtil.sendOkHttpRequest(2,2,"login.json/zero="+zero+"&password="+password);
                    responseData=response.body().string();
                    Log.e("login","登陆成功");
                    responseData=SecretUtil.Decrypt(responseData);
                    if(responseData.equals("true")){
                        listener.onResponse("true");
                    }
                } catch (IOException e) {
                    if(e instanceof SocketTimeoutException){
                        listener.onFailure(0);
                        Log.e("login","连接超时");
                    }else if(e instanceof ConnectException){
                        listener.onFailure(1);
                        Log.e("login","连接异常");
                    }else if(e instanceof SocketException){
                        listener.onFailure(2);
                        Log.e("login","无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
        return false;
    }
}
