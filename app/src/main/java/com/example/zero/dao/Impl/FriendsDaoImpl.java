package com.example.zero.dao.Impl;

import android.content.Context;
import android.util.Log;

import com.example.zero.bean.Group;
import com.example.zero.bean.ResponseWrapper;
import com.example.zero.dao.FriendsDao;
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
 * 日 期： 2018/2/22
 * 时 间： 14:38
 * 项 目： Zero
 * 描 述：
 */

public class FriendsDaoImpl implements FriendsDao {

    @Override
    public void getFriends(final Context context, final Listener<Group> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String zero= CacheUtil.getString(context,"zero");
                    String password=CacheUtil.getString(context,"password");
                    Response response= HttpUtil.sendOkHttpRequest(2,2,"friends/login_confirmation/friendsgroup.json?zero="+zero+"&password="+password);
                    String responseData=response.body().string();
                    ResponseWrapper<Group> wrapper=new Gson().fromJson(responseData,new TypeToken<ResponseWrapper<Group>>(){}.getType());
                    if (wrapper.isSuccess()) {
                        if (wrapper.getCode().equals("0000")) {
                            listener.onResponse(wrapper.getList());
                        } else if (wrapper.getCode().equals("0001")) {
                            listener.onFailure("没有分组");
                        }
                    } else {
                        if(wrapper.getCode().equals("0002")){
                            listener.onFailure(wrapper.getMsg());
                        }else if (wrapper.getCode().equals("0003")){
                            listener.onFailure("非法用户");
                        }

                    }
                } catch (IOException e) {
                    if (e instanceof SocketTimeoutException) {
                        listener.onFailure("连接超时");
                        Log.e("test-FriendsDaoImpl", "etFriends():连接超时");
                    } else if (e instanceof ConnectException) {
                        listener.onFailure("连接异常");
                        Log.e("test-FriendsDaoImpl", "etFriends():连接异常");
                    } else if (e instanceof SocketException) {
                        listener.onFailure("无法访问服务器(服务器地址出错)");
                        Log.e("test-FriendsDaoImpl", "etFriends():无法访问服务器(服务器地址出错)");
                    }
                }
            }
        }).start();
    }
}
