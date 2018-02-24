package com.example.zero.view.main.message;

import android.view.LayoutInflater;
import android.view.View;

import com.example.zero.R;
import com.example.zero.base.BaseFragment;
import com.example.zero.presenter.main.message.MessagePresenter;


public class MessageFragment extends BaseFragment<MessageView, MessagePresenter<MessageView>> implements MessageView {

    @Override
    protected MessagePresenter<MessageView> createPresenter() {
        return new MessagePresenter<>();
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_message, null);
        return view;
    }

    @Override
    public void initData() {

    }

}
