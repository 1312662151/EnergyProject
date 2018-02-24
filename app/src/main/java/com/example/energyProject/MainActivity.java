package com.example.energyProject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.energyProject.Adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.energyProject.Fragments.fourFragment;
import com.example.energyProject.Fragments.oneFragment;
import com.example.energyProject.Fragments.threeFragment;
import com.example.energyProject.Fragments.twoFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private DrawerLayout drawerLayout;
    private TextView tvTitle;
    private Button btnRight;

    //TabLayout标签
    private String[] titles = new String[]{"第一页", "第二页", "第三页", "第四页"};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        tvTitle = (TextView) findViewById(R.id.tv_toolbar);
        btnRight = (Button) findViewById(R.id.btnRight);


        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //Home打开侧边栏
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.backburger);
        }
        //toolbar右侧按钮点击事件
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActicity.class);
                startActivity(intent);
            }
        });
        //Tablayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//MODE_FIXED可滚动
        //循环注入标签
        for (String tab : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        tabLayout.addOnTabSelectedListener(this);

        fragments.add(new oneFragment());
        fragments.add(new twoFragment());
        fragments.add(new threeFragment());
        fragments.add(new fourFragment());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //Toolbar文字
    protected void setTitleName(String name) {
        tvTitle.setText(name);
    }


//    //Toolbar右侧menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

    //Tab方法
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

}
