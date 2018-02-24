package com.example.zero.dao.Impl;

import android.content.Context;
import android.util.Log;

import com.example.zero.bean.ResponseWrapper;
import com.example.zero.bean.Zero;
import com.example.zero.dao.Login2Dao;
import com.example.zero.utils.CacheUtil;
import com.example.zero.utils.HttpUtil;
import com.example.zero.utils.Listener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Response;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/6
 * 时 间： 14:08
 * 项 目： Zero
 * 描 述：
 */

public class Login2DaoImpl implements Login2Dao {

    @Override
    public void getIndentify(final String phone, final Listener<String> listener) {
        Log.e("test-Login2DaoImpl", "getIndentify()");
        EventHandler eh1 = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    Log.e("test-Login2DaoImpl", "getIndentify():回调完成");
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Log.e("test-Login2DaoImpl","getIndentify():提交验证码成功");
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        listener.onResponse(null);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    Log.e("Indentify-code", event + " " + phone);
                    ((Throwable) data).printStackTrace();
                    listener.onFailure("获取验证码出错");
                }
            }
        };
        SMSSDK.registerEventHandler(eh1); //注册短信回调
        SMSSDK.getVerificationCode("86", phone);
    }

    @Override
    public void sendIndentity(String phone, String indentify, final Listener<String> listener) {
        EventHandler eh2 = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Log.e("test-Login2DaoImpl","sendIndentify():提交验证码成功");
                        listener.onResponse(null);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    listener.onFailure("发送过程中出现错误");
                }
            }
        };
        SMSSDK.registerEventHandler(eh2);
        SMSSDK.submitVerificationCode("86", phone, indentify);
    }

    @Override
    public void Release() {
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public Boolean isSecond(Context context) {
        return CacheUtil.getBoolean(context, "first");
    }

    @Override
    public void isRegister(final String phone, final Listener<String> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String responseData = null;
                try {
                    Response response = HttpUtil.sendOkHttpRequest(2, 2, "index/isregister.json?zero=" + phone);
                    responseData = response.body().string();
                    Log.e("test-Login2DaoImpl", "isRegister():responseData:" + responseData);
                    ResponseWrapper<String> wrapper = new Gson().fromJson(responseData, new TypeToken<ResponseWrapper<String>>() {
                    }.getType());
                    Log.e("test-Login2DaoImpl", "isRegister():" + wrapper.isSuccess());
                    if (wrapper.isSuccess()) {
                        if (wrapper.getCode().equals("0000")) {
                            listener.onResponse(null);
//                            CacheUtil.putString(context,"zero",wrapper.getList().get(0).getZero());
//                            CacheUtil.putString(context,password,wrapper.getList().get(0).getPassword());
                        } else if (wrapper.getCode().equals("0001")) {
                            listener.onFailure("该手机号未注册！！");
                        }
                    } else {
                        listener.onFailure(wrapper.getMsg());
                    }
                } catch (Exception e) {
                    if (e instanceof SocketTimeoutException) {
                        listener.onFailure("连接超时");
                        Log.e("test-Login2DaoImpl", "isRegister():连接超时");
                    } else if (e instanceof ConnectException) {
                        listener.onFailure("连接异常");
                        Log.e("test-Login2DaoImpl", "isRegister():连接异常");
                    } else if (e instanceof SocketException) {
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("test-Login2DaoImpl", "isRegister():无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
    }

    @Override
    public void Login(final Context context, final String account, final Listener<Zero> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String responseData = null;
                try {
                    Response response = HttpUtil.sendOkHttpRequest(2, 2, "index/login2.json?zero="
                            + account);
                    responseData = response.body().string();
                    ResponseWrapper<Zero> wrapper = new Gson().fromJson(responseData, new TypeToken<ResponseWrapper<Zero>>() {
                    }.getType());
                    if (wrapper.isSuccess()) {
                        if (wrapper.getCode().equals("0000")) {
                            listener.onResponse(null);
                            CacheUtil.putString(context, "zero", wrapper.getList().get(0).getZero());
                            CacheUtil.putString(context, "password", wrapper.getList().get(0).getPassword());
                        } else if (wrapper.getCode().equals("0001") ) {
                            listener.onFailure("该手机号未注册！！");
                        }
                    } else {
                        listener.onFailure(wrapper.getMsg());
                    }
                } catch (Exception e) {
                    if (e instanceof SocketTimeoutException) {
                        listener.onFailure("连接超时");
                        Log.e("test-Login2DaoImpl", "Login():连接超时");
                    } else if (e instanceof ConnectException) {
                        listener.onFailure("连接异常");
                        Log.e("test-Login2DaoImpl", "Login():连接异常");
                    } else if (e instanceof SocketException) {
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("test-Login2DaoImpl", "Login():无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
    }

    @Override
    public void setRemember(Context context) {
        CacheUtil.putBoolean(context,"isRemember",true);
    }


}
