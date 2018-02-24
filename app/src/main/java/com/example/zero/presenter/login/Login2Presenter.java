package com.example.zero.presenter.login;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.zero.base.BasePresenter;
import com.example.zero.bean.Zero;
import com.example.zero.dao.Impl.Login2DaoImpl;
import com.example.zero.dao.Login2Dao;
import com.example.zero.utils.Listener;
import com.example.zero.view.login.Login2View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/6
 * 时 间： 10:36
 * 项 目： Zero
 * 描 述：
 */

public class Login2Presenter<V extends Login2View> extends BasePresenter<V> {

    private Context context;
    private Login2Dao login2Dao=new Login2DaoImpl();
    private Handler handler=new Handler();

    public Login2Presenter(Context context){
        this.context=context;
    }

    public void getIndentify(){
        if (isMobile(view.get().getAccount())){
            login2Dao.isRegister(view.get().getAccount(), new Listener<String>() {
                @Override
                public void onResponse(List<String> list) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            login2Dao.getIndentify(view.get().getAccount(), new Listener<String>() {
                                @Override
                                public void onResponse(List<String> list) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.e("test-Login2Presenter","getIndentify().onResponse()");
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
//                            Log.e("Indentify","hello");
//                            Toast.makeText(context,"haha",Toast.LENGTH_SHORT).show();
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
        login2Dao.Release();
        if (isMobile(view.get().getAccount())){
            if (isIndentify()){
                login2Dao.sendIndentity(view.get().getAccount(), view.get().getIndentify(), new Listener<String>() {
                    @Override
                    public void onResponse(List<String> list) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                login2Dao.Release();
                                login2Dao.Login(context, view.get().getAccount(), new Listener<Zero>() {
                                    @Override
                                    public void onResponse(List<Zero> list) {
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                login2Dao.Release();
                                                login2Dao.setRemember(context);
                                                if (login2Dao.isSecond(context)){
                                                    view.get().gotoMianActivity();
                                                }else {
                                                    view.get().gotoGuideActivity();
                                                }
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

    public boolean isMobile(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public boolean isIndentify(){
        if (view.get().getIndentify().isEmpty()) return false;
        return true;
    }

    public void Back(){
        view.get().Back();
    }

    public void setTime(){
        view.get().setTime();
    }

    public void setIndentify(){
        view.get().setIdentify();
    }
}
