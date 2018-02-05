package com.example.zero.dao;

import android.content.Context;

import com.example.zero.bean.Zero;
import com.example.zero.utils.Listener;


/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/4
 * 时 间： 13:06
 * 项 目： Zero
 * 描 述：
 */

public interface LoginDao {

    void getAcountList(Context context, Listener<String> listener);

    void getHead(String account, Listener<String> listener);

    void Login(Context context,String account, String password, Listener<Zero> listener);

    void setRemember(Context context);

}
