package com.example.zero.view.splash;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/26
 * 时 间： 13:50
 * 项 目： Zero
 */

public interface SplashView {

    void splashAnimation();

    void gotoMainActivity();

    void gotoLoginActivity();

    void timeOutException();

    void connectException();

    void socketException();
}
