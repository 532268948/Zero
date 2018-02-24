package com.example.zero.view.findpassword;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/14
 * 时 间： 13:21
 * 项 目： Zero
 * 描 述：
 */

public interface Password1View {

    String getAccount();

    String getIndentify();

    void gotoNext();

    void getFocus();

    void Back();

    void setTime();

    void setIndentify();

    void indentifySending();

    void errorSending();

    void errorIndentify();

    void emptyIndentify();

    void Error(String message);

    void errorPhone();

    void Start();

    void Stop();

    void setAccount();

}
