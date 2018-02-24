package com.example.zero.dao.Impl;

import android.content.Context;
import android.util.Log;

import com.example.zero.bean.ResponseWrapper;
import com.example.zero.bean.Zero;
import com.example.zero.dao.LoginDao;
import com.example.zero.utils.CacheUtil;
import com.example.zero.utils.HttpUtil;
import com.example.zero.utils.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/4
 * 时 间： 13:06
 * 项 目： Zero
 * 描 述：
 */

public class LoginDaoImpl implements LoginDao {
    @Override
    public void getAcountList(final Context context, final Listener<String> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = CacheUtil.getString(context, "zeroList");
                String[] account = response.split(";");
                List<String> list = new ArrayList<>();
                for (int i = 0; i < account.length; i++) {
                    list.add(account[i]);
                }
                listener.onResponse(list);
            }
        }).start();
    }

    @Override
    public void getHead(final String account, final Listener<String> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = HttpUtil.sendOkHttpRequest(2, 2, "index/head.json?zero=" + account);
                    String responseData = response.body().string();
                    ResponseWrapper<String> wrapper = new Gson().fromJson(responseData, new TypeToken<ResponseWrapper<String>>() {
                    }.getType());
                    Log.e("test", "head:"+responseData);
                    if (wrapper.isSuccess()) {
                        if (wrapper.getCode().equals("0000")) {
                            listener.onResponse(wrapper.getList());
                        } else if (wrapper.getCode().equals("0001")) {
                            listener.onFailure(wrapper.getMsg());
                        }
                    } else {
                        listener.onFailure(wrapper.getMsg());
                    }
                } catch (Exception e) {
                    if (e instanceof SocketTimeoutException) {
                        listener.onFailure("连接超时");
                        Log.e("login", "连接超时");
                    } else if (e instanceof ConnectException) {
                        listener.onFailure("连接异常");
                        Log.e("login", "连接异常");
                    } else if (e instanceof SocketException) {
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("login", "无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
    }

    @Override
    public void Login(final Context context, final String account, final String password, final Listener<Zero> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("test", "login1");
                    Response response = HttpUtil.sendOkHttpRequest(2, 2, "index/login.json?zero="
                            + account + "&password=" + password);
                    String responseData = response.body().string();
                    Log.e("test", "login2:"+responseData);
                    ResponseWrapper<Zero> wrapper = new Gson().fromJson(responseData, new TypeToken<ResponseWrapper<Zero>>() {
                    }.getType());
                    Log.e("test", "login3:" + wrapper.isSuccess());
                    if (wrapper.isSuccess()) {
                        if (wrapper.getCode().equals("0000")) {
                            Log.e("test","login4:0000");
                            listener.onResponse(null);
                            CacheUtil.putString(context, "zero", account);
                            CacheUtil.putString(context, "password", password);
                        } else if (wrapper.getCode().equals("0001")) {
                            listener.onFailure("该手机号未注册！！");
                        }
                    } else {
                        listener.onFailure(wrapper.getMsg());
                    }
                } catch (Exception e) {
                    if (e instanceof SocketTimeoutException) {
                        listener.onFailure("连接超时");
                        Log.e("login", "连接超时");
                    } else if (e instanceof ConnectException) {
                        listener.onFailure("连接异常");
                        Log.e("login", "连接异常");
                    } else if (e instanceof SocketException) {
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("login", "无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
    }

    @Override
    public void setRemember(final Context context, final Boolean is) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CacheUtil.putBoolean(context, "isRemember", is);
            }
        }).start();
    }
}
