package com.example.zero.dao;

import android.content.Context;

import com.example.zero.utils.Listener;


/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/27
 * 时 间： 12:52
 * 项 目： Zero
 */

public interface SplashDao {
    public Boolean isRemember(Context context);
    public Boolean Login(Context context, Listener<String> listener);
}
