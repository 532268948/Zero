package com.example.zero.presenter.spalsh;

import android.content.Context;
import android.os.Handler;

import com.example.zero.base.BasePresenter;
import com.example.zero.dao.Impl.SplashDaoImpl;
import com.example.zero.dao.SplashDao;
import com.example.zero.utils.Listener;
import com.example.zero.view.splash.SplashView;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/26
 * 时 间： 13:53
 * 项 目： Zero
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> {

    private Context context;
    private SplashDao splashDao=new SplashDaoImpl();
    private Handler handler=new Handler();

    public SplashPresenter(Context context){
        this.context=context;
    }

    public void fetch(){
        if(view!=null){
            view.get().splashAnimation();
        }
    }

    public boolean isRemember(){
        if(splashDao.isRemember(context)){
            return true;
        }
        return false;
    }

    public void Login(){
        splashDao.Login(context, new Listener<String>() {
            @Override
            public void onResponse(List<String> list) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().gotoMainActivity();
                    }
                });
            }

            @Override
            public void onFailure(final String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().False(message);
                    }
                });
            }
        });
    }

    public void goLogin(){
        if(view!=null){
            view.get().gotoLoginActivity();
        }
    }
}
