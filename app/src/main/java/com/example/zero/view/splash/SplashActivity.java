package com.example.zero.view.splash;

import android.os.Bundle;

import com.example.zero.R;
import com.example.zero.base.BaseActivity;
import com.example.zero.presenter.spalsh.SplashPresenter;

public class SplashActivity extends BaseActivity<SplashView,SplashPresenter<SplashView>> implements SplashView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected SplashPresenter<SplashView> createPresenter() {
        return new SplashPresenter<>();
    }

    @Override
    public void splashAnimation() {

    }
}
