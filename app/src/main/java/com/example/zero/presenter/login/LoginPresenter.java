package com.example.zero.presenter.login;

import android.content.Context;
import android.os.Handler;

import com.example.zero.base.BasePresenter;
import com.example.zero.bean.Zero;
import com.example.zero.dao.Impl.LoginDaoImpl;
import com.example.zero.dao.LoginDao;
import com.example.zero.utils.Listener;
import com.example.zero.view.login.LoginView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/2
 * 时 间： 15:32
 * 项 目： Zero
 * 描 述：
 */

public class LoginPresenter<V extends LoginView> extends BasePresenter<V> {

    private LoginDao loginDao=new LoginDaoImpl();
    private Context context;
    private Handler handler=new Handler();

    public LoginPresenter(Context context){
        this.context=context;
    }

    public void fetch(){
        loginDao.getAcountList(context, new Listener<String>() {
            @Override
            public void onResponse(final List<String> list) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().accountAdapter(list.toArray(new String[0]));
                    }
                });
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    /**
     * 获取头像
     */
    public void getHead(){
        loginDao.getHead(view.get().getAccount(), new Listener<String>() {
            @Override
            public void onResponse(final List<String> list) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().setHeadImage(list.get(0));
                    }
                });
            }

            @Override
            public void onFailure(String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().setEmptyHead();
                    }
                });
            }
        });
    }

    public void Login(){
        if (isMobile(view.get().getAccount())){
            if (isPassword(view.get().getPassword())){
                view.get().showLoading();
                if(view.get().CheckBox()){
                    loginDao.setRemember(context);
                }
                loginDao.Login(context, view.get().getAccount(), view.get().getPassword(), new Listener<Zero>() {
                    @Override
                    public void onResponse(List<Zero> list) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.get().hideLoading();
                                view.get().gotoMainActivity();
                            }
                        });
                    }

                    @Override
                    public void onFailure(final String message) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.get().hideLoading();
                                view.get().errorLogin(message);
                            }
                        });
                    }
                });
            }else {
                view.get().errorPassword();
            }
        }else {
            view.get().errorPhone();
        }
    }

    /**
     * 判断手机号是否正确
     * @param mobiles
     * @return
     */
    public boolean isMobile(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public boolean isPassword(String password){
        if(password.length()>5&&password.length()<19){
            return true;
        }
        return false;
    }

    public void gotoPasswordActivity(){
        view.get().gotoPasswordActivity();
    }

    public void gotoRegisterActivity(){
        view.get().gotoRegisterActivity();
    }

    public void gotoLogin2Activity(){
        view.get().gotoLogin2Activity();
    }
}
