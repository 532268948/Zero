package com.example.zero.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/26
 * 时 间： 13:23
 * 项 目： Zero
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends Activity{

    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=createPresenter();
        presenter.attachView((V) this);
//        Log.e("activity",this.toString());
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
