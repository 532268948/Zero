package com.example.zero.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zero.R;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/4
 * 时 间： 14:28
 * 项 目： Zero
 * 描 述：
 */

public class AccountAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    AccountAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_account,null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.account.setText(list.get(i));
        return view;
    }

    class ViewHolder{
        TextView account;
        ViewHolder(View view){
            account=(TextView)view.findViewById(R.id.accout);
        }
    }
}
