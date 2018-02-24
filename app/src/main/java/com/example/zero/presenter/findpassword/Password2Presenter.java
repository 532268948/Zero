package com.example.zero.presenter.findpassword;

import android.os.Handler;

import com.example.zero.base.BasePresenter;
import com.example.zero.dao.Impl.Password2DaoImpl;
import com.example.zero.dao.Password2Dao;
import com.example.zero.utils.Listener;
import com.example.zero.view.findpassword.Password2View;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/18
 * 时 间： 11:42
 * 项 目： Zero
 * 描 述：
 */

public class Password2Presenter<V extends Password2View> extends BasePresenter<V> {

    private Handler handler = new Handler();
    private Password2Dao password2Dao=new Password2DaoImpl();

    public void Revise() {
        if(Password(view.get().getPassword())){
            password2Dao.Revise(view.get().getAccount(),view.get().getPassword(), new Listener<String>() {
                @Override
                public void onResponse(List<String> list) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.get().Prompt("密码修改成功！");
                            view.get().gotoLoginActivity();
                        }
                    });
                }

                @Override
                public void onFailure(final String message) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.get().Prompt(message);
                        }
                    });
                }
            });
        }
    }

    private Boolean Password(String password) {
        if (password.length() > 5 && password.length() < 17) {
            return true;
        }
        return false;
    }

    public void Back() {
        view.get().Back();
    }

}
