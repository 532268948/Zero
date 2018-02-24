package com.example.zero.view.findpassword;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/18
 * 时 间： 11:35
 * 项 目： Zero
 * 描 述：
 */

public interface Password2View {

    void Prompt(String message);

    void Back();

    String getPassword();

    String getAccount();

    void gotoLoginActivity();

}
