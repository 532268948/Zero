package com.example.zero.view.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.zero.R;
import com.example.zero.base.BaseActivity;
import com.example.zero.presenter.login.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter<LoginView>> implements LoginView {

    private AutoCompleteTextView account;
    private String[] res = {"15869107730", "15869107732", "15869107731", "13634130075", "15968896759", "13634130074", "15968896758"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        account = (AutoCompleteTextView) findViewById(R.id.accout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        account.setAdapter(adapter);
//        AndroidBug5497Workaround.assistActivity(this);
    }

    @Override
    protected LoginPresenter<LoginView> createPresenter() {
        return new LoginPresenter<>();
    }
}

