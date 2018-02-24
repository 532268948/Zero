package com.example.zero.presenter.findpassword;

import android.os.Handler;

import com.example.zero.base.BasePresenter;
import com.example.zero.bean.Zero;
import com.example.zero.dao.Impl.Password1DaoImpl;
import com.example.zero.dao.Password1Dao;
import com.example.zero.utils.Listener;
import com.example.zero.view.findpassword.Password1View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/14
 * 时 间： 13:22
 * 项 目： Zero
 * 描 述：
 */

public class Password1Presenter<V extends Password1View> extends BasePresenter<V> {

    private Handler handler=new Handler();
    Password1Dao password1Dao=new Password1DaoImpl();

    public void getIndentify(){
        if (isMobile(view.get().getAccount())){
            password1Dao.isRegister(view.get().getAccount(), new Listener<String>() {
                @Override
                public void onResponse(List<String> list) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.get().Start();
                            password1Dao.getIndentify(view.get().getAccount(), new Listener<String>() {
                                @Override
                                public void onResponse(List<String> list) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            view.get().indentifySending();
                                            view.get().getFocus();
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(String message) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            view.get().errorSending();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

                @Override
                public void onFailure(final String message) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.get().Error(message);
                        }
                    });
                }
            });
        }else {
            view.get().errorPhone();
        }
    }

    public void sendIndentify(){
        password1Dao.Release();
        if (isMobile(view.get().getAccount())){
            if (isIndentify()){
                password1Dao.sendIndentity(view.get().getAccount(), view.get().getIndentify(), new Listener<String>() {
                    @Override
                    public void onResponse(List<String> list) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.get().setAccount();
                                view.get().gotoNext();
                            }
                        });
                    }

                    @Override
                    public void onFailure(String message) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.get().errorIndentify();
                            }
                        });
                    }
                });
            }else {
                view.get().emptyIndentify();
            }
        }else {
            view.get().errorPhone();
        }
    }

    public void Back(){
        view.get().Back();
    }

    public boolean isMobile(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public boolean isIndentify(){
        if (view.get().getIndentify().isEmpty()) return false;
        return true;
    }

    public void setTime(){
        view.get().setTime();
    }

    public void setIndentify(){
        view.get().setIndentify();
    }

}
