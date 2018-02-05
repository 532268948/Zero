package com.example.zero.view.login;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/2
 * 时 间： 15:31
 * 项 目： Zero
 * 描 述：
 */

public interface LoginView {

    void accountAdapter(String[] account);

    void setHeadImage(String url);

    void setEmptyHead();

    String getAccount();

    String getPassword();

    void gotoPasswordActivity();

    void gotoRegisterActivity();

    void gotoLogin2Activity();

    void gotoMainActivity();

    void showLoading();

    void hideLoading();

    void errorPhone();

    void errorPassword();

    void errorLogin(String message);

    Boolean CheckBox();
}
