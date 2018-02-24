package com.example.zero.dao;

import com.example.zero.utils.Listener;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/17
 * 时 间： 12:15
 * 项 目： Zero
 * 描 述：
 */

public interface Password1Dao {

    void getIndentify(final String phone, final Listener<String> listener);

    void sendIndentity(String phone, String indentify, final Listener<String> listener);

    void Release();

    void isRegister(final String phone, final Listener<String> listener);
}
