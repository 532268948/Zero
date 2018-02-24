package com.example.zero.dao;

import android.content.Context;

import com.example.zero.bean.Group;
import com.example.zero.utils.Listener;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/22
 * 时 间： 14:38
 * 项 目： Zero
 * 描 述：
 */

public interface FriendsDao {

    void getFriends(Context context, Listener<Group> listener);

}
