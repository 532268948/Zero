package com.example.zero.view.main.friends;

import com.example.zero.bean.Group;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/21
 * 时 间： 14:42
 * 项 目： Zero
 * 描 述：
 */

public interface FriendsView {

    void setAdapter(List<Group> list);

    void Error(String message);

}
