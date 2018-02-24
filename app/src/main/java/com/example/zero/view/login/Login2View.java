package com.example.zero.view.login;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/6
 * 时 间： 10:35
 * 项 目： Zero
 * 描 述：
 */

public interface Login2View {

    void Back();

    String getAccount();

    String getIndentify();

    void setTime();

    void setIdentify();

    void errorPhone();

    void indentifySending();

    void errorSending();

    void errorIndentify();

    void emptyIndentify();

    void gotoMianActivity();

    void gotoGuideActivity();

    void getFocus();

    void Error(String message);

}
