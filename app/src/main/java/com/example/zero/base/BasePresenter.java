package com.example.zero.base;

import java.lang.ref.WeakReference;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/1/26
 * 时 间： 13:23
 * 项 目： Zero
 */

public class BasePresenter<V> {

    /**
     * view的弱引用
     *  @param view
     */
    protected WeakReference<V> view;

//    /**
//     *
//     * @param context
//     */
//    protected WeakReference<C> context;

    /**
     * view绑定
     * @param view
     */
    public void attachView(V view){
        this.view=new WeakReference<V>(view);
//        this.context=new WeakReference<C>(context);
    }

    /**
     * view解绑
     */
    public void detachView(){
        this.view.clear();
//        this.context.clear();
    }
}
