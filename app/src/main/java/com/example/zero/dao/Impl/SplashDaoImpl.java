package com.example.zero.dao.Impl;

import android.content.Context;
import android.util.Log;

import com.example.zero.bean.ResponseWrapper;
import com.example.zero.dao.SplashDao;
import com.example.zero.utils.CacheUtil;
import com.example.zero.utils.HttpUtil;
import com.example.zero.utils.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    public Boolean Login(final Context context, final Listener<String> listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String zero= CacheUtil.getString(context,"zero");
                String password=CacheUtil.getString(context,"password");
                String responseData=null;
                try {
                    Response response = HttpUtil.sendOkHttpRequest(2,2,"login.json/zero="+zero+"&password="+password);
                    responseData=response.body().string();
                    Log.e("login","登陆成功");
                    ResponseWrapper<String> wrapper=new Gson().fromJson(responseData,new TypeToken<ResponseWrapper<String>>(){}.getType());
                    if(wrapper.isSuccess()){
                        if(wrapper.getCode()=="0000"){
                            listener.onResponse(null);
                        }else {
                            listener.onFailure(wrapper.getMsg());
                        }
                    }else {
                        listener.onFailure(wrapper.getMsg());
                    }
                } catch (IOException e) {
                    if(e instanceof SocketTimeoutException){
                        listener.onFailure("连接超时");
                        Log.e("login","连接超时");
                    }else if(e instanceof ConnectException){
                        listener.onFailure("连接异常");
                        Log.e("login","连接异常");
                    }else if(e instanceof SocketException){
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("login","无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
        return false;
    }
}
