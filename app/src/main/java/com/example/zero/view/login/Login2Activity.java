package com.example.zero.view.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zero.R;
import com.example.zero.base.BaseActivity;
import com.example.zero.presenter.login.Login2Presenter;
import com.example.zero.view.guide.GuideActivity;
import com.example.zero.view.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Login2Activity extends BaseActivity<Login2View,Login2Presenter<Login2View>> implements Login2View{

    private LinearLayout mBack;
    private EditText mAccount;
    private EditText mIndentify;
    private TextView mSend;
    private TextView mLogin;
    private Boolean flag=false;
    private int second=60;
    private Timer timer;
    private TimerTask task;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mBack = (LinearLayout) findViewById(R.id.back);
        mAccount = (EditText) findViewById(R.id.account);
        mIndentify = (EditText) findViewById(R.id.indentify);
        mSend = (TextView) findViewById(R.id.send);
        mLogin = (TextView) findViewById(R.id.login);
        initListener();
    }

    private void initListener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Back();
            }
        });
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) return;
                startTimer();
                presenter.getIndentify();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendIndentify();
            }
        });
    }

    public void startTimer(){
        second=60;
        if(timer==null){
            timer=new Timer();
        }
        if(task==null){
            task=new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(second!=1){
                                --second;
                                presenter.setTime();
                            }else if(second==1){
                                stopTimer();
                                presenter.setIndentify();
                            }
                        }
                    });
                }
            };
        }
        flag=true;
        timer.schedule(task,0,1000);
    }

    public void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(task!=null){
            task.cancel();
            task=null;
        }
        flag=false;
    }

    @Override
    protected Login2Presenter<Login2View> createPresenter() {
        return new Login2Presenter<>(this);
    }

    @Override
    public void onBackPressed() {
        presenter.Back();
    }

    @Override
    public void Back() {
        this.finish();
    }

    @Override
    public String getAccount() {
        return mAccount.getText().toString();
    }

    @Override
    public String getIndentify() {
        return mIndentify.getText().toString();
    }

    @Override
    public void setTime() {
        mSend.setText("("+ second +")");
    }

    @Override
    public void setIdentify() {
        mSend.setText("send");
    }

    @Override
    public void errorPhone() {
        Toast.makeText(this,"手机号不正确",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void indentifySending() {
        Toast.makeText(this,"验证码已发送,请注意查收！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorSending() {
        Toast.makeText(this,"验证码发送失败，请稍后重试！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorIndentify() {
        Toast.makeText(this,"验证码不正确，请重新获取！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emptyIndentify() {
        Toast.makeText(this,"验证码不能为空！！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoMianActivity() {
        stopTimer();
        this.finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void gotoGuideActivity() {
        stopTimer();
        this.finish();
        startActivity(new Intent(this, GuideActivity.class));
    }

    @Override
    public void getFocus() {
        mIndentify.requestFocus();
    }

    @Override
    public void Error(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
