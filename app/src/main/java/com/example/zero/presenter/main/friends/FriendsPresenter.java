package com.example.zero.presenter.main.friends;

import android.content.Context;
import android.os.Handler;

import com.example.zero.base.BasePresenter;
import com.example.zero.bean.Group;
import com.example.zero.dao.FriendsDao;
import com.example.zero.dao.Impl.FriendsDaoImpl;
import com.example.zero.utils.Listener;
import com.example.zero.view.main.friends.FriendsView;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/21
 * 时 间： 14:44
 * 项 目： Zero
 * 描 述：
 */

public class FriendsPresenter<V extends FriendsView> extends BasePresenter<V> {

    private Handler handler=new Handler();
    private FriendsDao friendsDao=new FriendsDaoImpl();
    private Context context;

    public FriendsPresenter(Context context){
        this.context=context;
    }

    public void getFriends(){
        friendsDao.getFriends(context, new Listener<Group>() {
            @Override
            public void onResponse(final List<Group> list) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().setAdapter(list);
                    }
                });
            }

            @Override
            public void onFailure(final String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.get().Error(message);
                    }
                });
            }
        });
    }

}
