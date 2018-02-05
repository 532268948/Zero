package com.example.zero.utils;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/30
 * 时 间： 14:32
 * 项 目： Zero
 */

public interface Listener<T> {
    public void onResponse(List<T> list);
    public void onFailure(String message);
}
