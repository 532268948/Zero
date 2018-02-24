package com.example.zero.view.findpassword;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zero.R;
import com.example.zero.base.BaseFragment;
import com.example.zero.presenter.findpassword.Password2Presenter;


public class Password2Fragment extends BaseFragment<Password2View,Password2Presenter<Password2View>> implements Password2View{

    private LinearLayout mBack;
    private EditText mPassword;
    private TextView mSure;

    @Override
    protected Password2Presenter<Password2View> createPresenter() {
        return new Password2Presenter<>();
    }

    @Override
    public View initView() {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_password2,null);
        mBack=(LinearLayout)view.findViewById(R.id.back);
        mPassword=(EditText) view.findViewById(R.id.password);
        mSure=(TextView)view.findViewById(R.id.sure);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void Prompt(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Back() {
        ((PasswordActivity)getActivity()).Back();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public String getAccount() {
        return ((PasswordActivity)getActivity()).getAccount();
    }

    @Override
    public void gotoLoginActivity() {
        getActivity().finish();
    }
}
