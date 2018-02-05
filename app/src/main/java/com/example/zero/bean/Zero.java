package com.example.zero.bean;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/5
 * 时 间： 14:20
 * 项 目： Zero
 * 描 述：
 */

public class Zero {
    String zero;
    String password;

    public Zero(String zero, String password) {
        this.zero = zero;
        this.password = password;
    }

    public String getZero() {
        return zero;
    }

    public void setZero(String zero) {
        this.zero = zero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
