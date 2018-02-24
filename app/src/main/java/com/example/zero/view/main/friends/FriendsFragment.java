package com.example.zero.view.main.friends;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.zero.R;
import com.example.zero.adapter.FriendsListAdapter;
import com.example.zero.base.BaseFragment;
import com.example.zero.bean.Group;
import com.example.zero.presenter.main.friends.FriendsPresenter;

import java.util.List;

public class FriendsFragment extends BaseFragment<FriendsView, FriendsPresenter<FriendsView>> implements FriendsView {

    private ExpandableListView friendsList;
    private FriendsListAdapter adapter;
//    public String[] groupStrings = {"西游记", "水浒传", "三国演义", "红楼梦"};
//    public String[][] childStrings = {
//            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
//            {"宋江", "林冲", "李逵", "鲁智深"},
//            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
//            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
//    };

    @Override
    protected FriendsPresenter<FriendsView> createPresenter() {
        return new FriendsPresenter<>(getActivity());
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_friends, null);
        if (friendsList == null) {
            friendsList = (ExpandableListView) view.findViewById(R.id.friends_list);
        }
//        if (adapter == null) {
//            adapter = new FriendsListAdapter(getContext(), groupStrings, childStrings);
//        }
//        friendsList.setAdapter(adapter);
        //        设置分组项的点击监听事件
//        friendsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
////                Toast.makeText(getApplicationContext(), groupStrings[i], Toast.LENGTH_SHORT).show();
//                // 请务必返回 false，否则分组不会展开
//                return false;
//            }
//        });
////        设置子选项点击监听事件
//
//        friendsList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
//                                        int childPosition, long id) {
////                    Toast.makeText(getApplicationContext(), childStrings[groupPosition][childPosition], Toast.LENGTHshow();
//                return true;
//            }
//        });
        return view;
    }

    @Override
    public void initData() {
        presenter.getFriends();
        isLoad=true;
    }

    @Override
    public void setAdapter(List<Group> list) {
        adapter=new FriendsListAdapter(getContext(),list);
        friendsList.setAdapter(adapter);
    }

    @Override
    public void Error(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}
