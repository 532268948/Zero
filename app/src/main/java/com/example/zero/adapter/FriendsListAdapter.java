package com.example.zero.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zero.R;
import com.example.zero.bean.Group;
import com.example.zero.utils.Listener;
import com.example.zero.utils.UrlUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/22
 * 时 间： 15:35
 * 项 目： Zero
 * 描 述：
 */

public class FriendsListAdapter extends BaseExpandableListAdapter {

    private List<Group> list;
    private Context context;

    public FriendsListAdapter(Context context,List<Group> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        int online=0;
        GroupViewHolder groupViewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_group,parent,false);
            groupViewHolder=new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder=(GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.group.setText(list.get(groupPosition).getSname());
        setIndicatorState(groupViewHolder.indicator,groupPosition,isExpanded);
        for(int i=0;i<list.get(groupPosition).getList().size();i++){
            if(list.get(groupPosition).getList().get(i).getStatus()==1){
                online++;
            }
        }
        groupViewHolder.online.setText(online+" / "+list.get(groupPosition).getList().size());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_friends,parent,false);
            childViewHolder=new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder=(ChildViewHolder)convertView.getTag();
        }
        childViewHolder.child.setText(list.get(groupPosition).getList().get(childPosition).getNickname());
        Glide.with(context).load(UrlUtil.setUrl(list.get(groupPosition).getList().get(childPosition).getHead())).into(childViewHolder.head);
        if(list.get(groupPosition).getList().get(childPosition).getStatus()==0){
            childViewHolder.offline.setVisibility(View.VISIBLE);
        }else {
            childViewHolder.offline.setVisibility(View.GONE);
        }
        Log.e("test_FriendsListAdapter","getChildView(Param...)"+list.get(groupPosition).getList().get(childPosition).getStatus());
        return convertView;
    }

    public void setIndicatorState(ImageView mIndicators,int groupPosition, boolean isExpanded) {
        if (isExpanded) {
            mIndicators.setImageResource(R.drawable.ic_expand);
        } else {
            mIndicators.setImageResource(R.drawable.ic_collapse);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        TextView group;
        ImageView indicator;
        TextView online;
        GroupViewHolder(View view){
            group=(TextView)view.findViewById(R.id.groupname);
            indicator=(ImageView)view.findViewById(R.id.group_indicator);
            online=(TextView)view.findViewById(R.id.online_number);
        }
    }

    class ChildViewHolder{
        TextView child;
        CircleImageView head;
        RelativeLayout offline;
        ChildViewHolder(View view){
            child=(TextView)view.findViewById(R.id.nickname);
            head=(CircleImageView)view.findViewById(R.id.head);
            offline=(RelativeLayout)view.findViewById(R.id.isonline);
        }
    }
}
