package com.example.zero.dao.Impl;

import android.util.Log;

import com.example.zero.bean.ResponseWrapper;
import com.example.zero.dao.Password2Dao;
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
 * 日 期： 2018/2/18
 * 时 间： 11:42
 * 项 目： Zero
 * 描 述：
 */

public class Password2DaoImpl implements Password2Dao {
    @Override
    public void Revise(final String phone, final String password, final Listener<String> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = HttpUtil.sendOkHttpRequest(2, 2, "index/revisepassword.json?zero="+phone+"&password=" + password);
                    String responseData = response.body().string();
                    ResponseWrapper<String> wrapper = new Gson().fromJson(responseData, new TypeToken<ResponseWrapper<String>>() {
                    }.getType());
                    if (wrapper.isSuccess()) {
                        if (wrapper.getCode().equals("0000")) {
                            listener.onResponse(null);
                        } else if (wrapper.getCode().equals("0001")) {
                            listener.onFailure("该手机号未注册！！");
                        }
                    } else {
                        listener.onFailure(wrapper.getMsg());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (e instanceof SocketTimeoutException) {
                        listener.onFailure("连接超时");
                        Log.e("test-Password2DaoImpl", "Revise():连接超时");
                    } else if (e instanceof ConnectException) {
                        listener.onFailure("连接异常");
                        Log.e("test-Password2DaoImpl", "Revise():连接异常");
                    } else if (e instanceof SocketException) {
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("test-Password2DaoImpl", "Revise():无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
    }
}
