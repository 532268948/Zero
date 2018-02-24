package com.example.zero.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/26
 * 时 间： 14:19
 * 项 目： Zero
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected View mRootView;
    public Context mContext;
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isLoad = false;
    public T presenter;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        presenter = createPresenter();
        presenter.attachView((V) this);
        setHasOptionsMenu(true);
    }

    protected abstract T createPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = initView();
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isPrepared = true;
        lazyLoad();
    }


    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

        if (!isLoad) {
            //loadAnimation();
            initData();
        }
    }

    protected void onInvisible() {

    }

    public abstract View initView();

    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    //public abstract void loadAnimation();

}
