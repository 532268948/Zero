package com.example.zero.view.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zero.R;
import com.example.zero.base.BaseActivity;
import com.example.zero.presenter.login.LoginPresenter;
import com.example.zero.view.findpassword.PasswordActivity;
import com.example.zero.view.main.MainActivity;
import com.example.zero.view.register.RegisterActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter<LoginView>> implements LoginView {

    private CircleImageView mHead;
    private EditText mAccount;
    private AutoCompleteTextView accountList;
    private EditText mPassword;
    private CheckBox mRemenber;
    private TextView mFindPassword;
    private TextView mLogin;
    private TextView mShortMessage;
    private TextView mRegister;
    private LinearLayout mLoad;
    private ImageView mLoadingImg;

    ObjectAnimator animator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mHead = (CircleImageView) findViewById(R.id.head);
        mAccount = (EditText) findViewById(R.id.accout);
        accountList = (AutoCompleteTextView) findViewById(R.id.accout);
        mPassword = (EditText) findViewById(R.id.password);
        mRemenber = (CheckBox) findViewById(R.id.check);
        mFindPassword =(TextView) findViewById(R.id.find_password);
        mLogin=(TextView) findViewById(R.id.login);
        mShortMessage = (TextView) findViewById(R.id.sms);
        mRegister = (TextView) findViewById(R.id.register);
        mLoad = (LinearLayout) findViewById(R.id.loading);
        mLoadingImg = (ImageView) findViewById(R.id.loadingImg);
        presenter.fetch();
        initListener();
//        AndroidBug5497Workaround.assistActivity(this);
    }

    private void initListener() {
        mAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()==11){
                    presenter.getHead();
                }
            }
        });
        mFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {presenter.gotoPasswordActivity();
            }
        });
        mShortMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.gotoLogin2Activity();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.gotoRegisterActivity();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Login();
            }
        });
    }

    @Override
    protected LoginPresenter<LoginView> createPresenter() {
        return new LoginPresenter<>(this);
    }

    @Override
    public void accountAdapter(String[] account) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, account);
        accountList.setAdapter(adapter);
    }

    @Override
    public void setHeadImage(String url) {
        Glide.with(this).load(url).into(mHead);
    }

    @Override
    public void setEmptyHead() {
        Glide.with(this).load(R.drawable.zero).into(mHead);
    }

    @Override
    public String getAccount() {
        return (mAccount.getText().toString());
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public void gotoPasswordActivity() {
        startActivity(new Intent(this, PasswordActivity.class));
    }

    @Override
    public void gotoRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void gotoLogin2Activity() {
        startActivity(new Intent(this,Login2Activity.class));
    }

    @Override
    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showLoading() {
        mLoad.setVisibility(View.VISIBLE);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(mLoad,"alpha",0f,1f);
        animator2=ObjectAnimator.ofFloat(mLoadingImg,"rotation",0,360);
        animator1.setDuration(500);
        animator1.start();
        animator2.setDuration(2000);
        animator2.start();
        animator2.setRepeatCount(ObjectAnimator.INFINITE);
    }

    @Override
    public void hideLoading() {
        ObjectAnimator animator1=ObjectAnimator.ofFloat(mLoad,"alpha",1f,0f);
        animator1.setDuration(500);
        animator1.start();
        animator2.cancel();
        mLoad.setVisibility(View.GONE);
    }

    @Override
    public void errorPhone() {
        Toast.makeText(this,"手机号格式错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorPassword() {
        Toast.makeText(this,"密码必须在6—18位之间",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorLogin(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Boolean CheckBox() {
        return mRemenber.isChecked();
    }
}

