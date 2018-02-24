package com.example.zero.view.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zero.R;
import com.example.zero.bean.ResponseWrapper;
import com.example.zero.utils.CacheUtil;
import com.example.zero.utils.HttpUtil;
import com.example.zero.utils.UrlUtil;
import com.example.zero.view.main.friends.FriendsFragment;
import com.example.zero.view.main.message.MessageFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MessageFragment fragment1 = new MessageFragment();
    private FriendsFragment fragment2 = new FriendsFragment();
    private MessageFragment fragment3 = new MessageFragment();
    private final int[] TAB_IMGS = new int[]{R.drawable.message_selector, R.drawable.friends_selector, R.drawable.friends_selector};
    private final Fragment[] TAB_FRAGMENTS = new Fragment[]{fragment1, fragment2, fragment3};
    private TabLayout mTabLayout;
    private MyViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private CircleImageView mHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mHead=(CircleImageView)findViewById(R.id.head);
        navigationView.setNavigationItemSelectedListener(this);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        setTabs(mTabLayout,this.getLayoutInflater(),TAB_IMGS);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        getHead();
        mHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(navigationView);
            }
        });
    }

    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabImgs){
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom,null);
            tab.setCustomView(view);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void getHead(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String zero= CacheUtil.getString(MainActivity.this,"zero");
                    Response response = HttpUtil.sendOkHttpRequest(2,2,"index/head.json?zero="+zero);
                    String responseData=response.body().string();
                    Log.e("test_MainActivity","getHead()"+responseData);
                    final ResponseWrapper<String> wrapper=new Gson().fromJson(responseData,new TypeToken<ResponseWrapper<String>>(){}.getType());
                    if(wrapper.isSuccess()){
                        if(wrapper.getCode().equals("0000")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.with(MainActivity.this).load(UrlUtil.setUrl(wrapper.getList().get(0))).into(mHead);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
