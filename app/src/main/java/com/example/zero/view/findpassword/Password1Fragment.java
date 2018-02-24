package com.example.zero.view.findpassword;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zero.R;
import com.example.zero.base.BaseFragment;
import com.example.zero.presenter.findpassword.Password1Presenter;

import java.util.Timer;
import java.util.TimerTask;

public class Password1Fragment extends BaseFragment<Password1View, Password1Presenter<Password1View>> implements Password1View {

    private int second = 60;
    private Timer timer;
    private TimerTask task;
    private LinearLayout back;
    private EditText account, indentify;
    private TextView send, next;
    private Handler handler = new Handler();
    private Boolean flag = false;

    @Override
    protected Password1Presenter<Password1View> createPresenter() {
        return new Password1Presenter<>();
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_password1, null);
        Log.e("test-Password1Fragment", "initView()");
        back = (LinearLayout) view.findViewById(R.id.back);
        account = (EditText) view.findViewById(R.id.account);
        indentify = (EditText) view.findViewById(R.id.indentify);
        send = (TextView) view.findViewById(R.id.send);
        next = (TextView) view.findViewById(R.id.next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test-Password1Fragment", "backonclick1");
                presenter.Back();
                Log.e("test-Password1Fragment", "backonclick2");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) return;
//                startTimer();
                presenter.getIndentify();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendIndentify();
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }

    public void startTimer() {
        second = 60;
        if (timer == null) {
            timer = new Timer();
        }
        if (task == null) {
            task = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (second != 1) {
                                --second;
                                presenter.setTime();
                            } else if (second == 1) {
                                stopTimer();
                                presenter.setIndentify();
                            }
                        }
                    });
                }
            };
        }
        flag = true;
        timer.schedule(task, 0, 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        flag = false;
    }

    @Override
    public String getAccount() {
        return account.getText().toString();
    }

    @Override
    public String getIndentify() {
        return indentify.getText().toString();
    }

    @Override
    public void gotoNext() {
        stopTimer();
        ((PasswordActivity) getActivity()).replacePassword2();
    }

    @Override
    public void getFocus() {
        indentify.requestFocus();
    }

    @Override
    public void Back() {
        stopTimer();
        ((PasswordActivity) getActivity()).onBackPressed();
    }

    @Override
    public void setTime() {
        send.setText("(" + second + ")");
    }

    @Override
    public void setIndentify() {
        send.setText("send");
    }

    @Override
    public void indentifySending() {
        Toast.makeText(getContext(), "验证码已发送,请注意查收！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorSending() {
        Toast.makeText(getContext(), "验证码发送失败，请稍后重试！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorIndentify() {
        Toast.makeText(getContext(), "验证码不正确，请重新获取！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emptyIndentify() {
        Toast.makeText(getContext(), "验证码不能为空！！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorPhone() {
        Toast.makeText(getContext(), "手机号不正确", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Start() {
        startTimer();
    }

    @Override
    public void Stop() {
        stopTimer();
    }

    @Override
    public void setAccount() {
        ((PasswordActivity)getActivity()).setAccount(account.getText().toString());
    }
}
