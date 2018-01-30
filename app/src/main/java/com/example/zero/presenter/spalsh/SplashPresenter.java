package com.example.zero.presenter.spalsh;

import android.content.Context;
import android.util.Log;

import com.example.zero.base.BasePresenter;
import com.example.zero.dao.Impl.SplashDaoImpl;
import com.example.zero.dao.SplashDao;
import com.example.zero.utils.StringListener;
import com.example.zero.view.splash.SplashView;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/26
 * 时 间： 13:53
 * 项 目： Zero
 */

public class SplashPresenter<V extends SplashView> extends BasePresenter<V> {

    private Context context;
    private SplashDao splashDao=new SplashDaoImpl();

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
        splashDao.Login(context, new StringListener() {
            @Override
            public void onResponse(String response) {
                view.get().gotoMainActivity();
            }

            @Override
            public void onFailure(Integer code) {
                if (code==0){
                    view.get().timeOutException();
                }else if (code==1){
                    view.get().connectException();
                }else if(code==2){
                    view.get().socketException();
                }
            }
        });
    }

    public void goLogin(){
        if(view!=null){
            view.get().gotoLoginActivity();
        }
    }
}
