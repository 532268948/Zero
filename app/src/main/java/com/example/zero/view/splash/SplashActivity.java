package com.example.zero.view.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zero.R;
import com.example.zero.base.BaseActivity;
import com.example.zero.presenter.spalsh.SplashPresenter;
import com.example.zero.view.login.LoginActivity;
import com.example.zero.view.main.MainActivity;

public class SplashActivity extends BaseActivity<SplashView,SplashPresenter<SplashView>> implements SplashView{

    private ImageView zero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        zero=(ImageView)findViewById(R.id.zero);
        presenter.fetch();
    }

    @Override
    protected SplashPresenter<SplashView> createPresenter() {
        return new SplashPresenter<>(this);
    }

    @Override
    public void splashAnimation() {
        float y=zero.getTranslationY();
        ObjectAnimator animator1=ObjectAnimator.ofFloat(zero,"translationY",y+100f,y);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(zero,"alpha",0f,1f);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(animator1).with(animator2);
        animatorSet.setDuration(1500);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(presenter.isRemember()){
                    presenter.Login();
                }else {

                    presenter.goLogin();
                }
            }
        });
    }

    @Override
    public void gotoMainActivity() {
        this.finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void gotoLoginActivity() {
        this.finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void timeOutException() {
        Toast.makeText(this,"连接超时",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectException() {
        Toast.makeText(this,"连接异常",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void socketException() {
        Toast.makeText(this,"无法连接服务器",Toast.LENGTH_SHORT).show();
    }
}
