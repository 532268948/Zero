package com.example.zero.view.findpassword;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zero.R;

public class PasswordActivity extends AppCompatActivity {

    int flag=1;
    Password1Fragment password1Fragment=new Password1Fragment();
    Password2Fragment password2Fragment=new Password2Fragment();
    String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,password1Fragment).commit();
    }

    void replacePassword1(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,password1Fragment).commit();
        flag=1;
    }

    void replacePassword2(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,password2Fragment).commit();
        flag=2;
    }

    @Override
    public void onBackPressed() {
        Back();
    }

    void Back(){
        if(flag==1){
            this.finish();
        }else if(flag==2){
            replacePassword1();
        }
    }

    void setAccount(String account){
        this.account=account;
    }

    String getAccount(){
        return this.account;
    }
}
