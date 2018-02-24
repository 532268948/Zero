package com.example.zero.dao;

import android.content.Context;

import com.example.zero.bean.Zero;
import com.example.zero.utils.Listener;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/6
 * 时 间： 14:07
 * 项 目： Zero
 * 描 述：
 */

public interface Login2Dao {

    void getIndentify(String phone, Listener<String> listener);

    void sendIndentity(String phone,String indentify,Listener<String> listener);

    void Release();

    Boolean isSecond(Context context);

    void isRegister(String phone,Listener<String> listener );

    void Login(Context context, String account, Listener<Zero> listener);

    void setRemember(Context context);

}
