package com.example.zero.dao;

import com.example.zero.utils.Listener;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/18
 * 时 间： 11:41
 * 项 目： Zero
 * 描 述：
 */

public interface Password2Dao {

    void Revise(String phone,String password, Listener<String> listener);

}
