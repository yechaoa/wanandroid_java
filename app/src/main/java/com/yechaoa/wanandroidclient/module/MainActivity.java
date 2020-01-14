package com.yechaoa.wanandroidclient.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.yechaoa.wanandroidclient.R;
import com.yechaoa.wanandroidclient.adapter.CommonViewPagerAdapter;
import com.yechaoa.wanandroidclient.common.GlobalConstant;
import com.yechaoa.wanandroidclient.module.about.AboutActivity;
import com.yechaoa.wanandroidclient.module.collect.CollectActivity;
import com.yechaoa.wanandroidclient.module.home.HomeFragment;
import com.yechaoa.wanandroidclient.module.login.LoginActivity;
import com.yechaoa.wanandroidclient.module.navi.NaviFragment;
import com.yechaoa.wanandroidclient.module.project.ProjectFragment;
import com.yechaoa.wanandroidclient.module.search.SearchActivity;
import com.yechaoa.wanandroidclient.module.tree.TreeFragment;
import com.yechaoa.yutils.ActivityUtil;
import com.yechaoa.yutils.SpUtil;
import com.yechaoa.yutils.ToastUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        initDrawerLayout();

        initFragments();

        initListener();
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initFragments() {
        CommonViewPagerAdapter infoPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager());
        infoPagerAdapter.addFragment(new HomeFragment());
        infoPagerAdapter.addFragment(new TreeFragment());
        infoPagerAdapter.addFragment(new NaviFragment());
        infoPagerAdapter.addFragment(new ProjectFragment());
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(infoPagerAdapter);
    }

    private void initListener() {
        mNavView.setNavigationItemSelectedListener(this);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * ViewPager滑动事件
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mBottomNavigation.getMenu().getItem(position).setChecked(true);
            switch (position) {
                case 0:
                    mToolbar.setTitle(getResources().getString(R.string.title_home));
                    break;
                case 1:
                    mToolbar.setTitle(getResources().getString(R.string.title_tree));
                    break;
                case 2:
                    mToolbar.setTitle(getResources().getString(R.string.title_navi));
                    break;
                default:
                    mToolbar.setTitle(getResources().getString(R.string.title_project));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 底部菜单事件
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_tree:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_navi:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_project:
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    /**
     * toolbar菜单事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 侧边栏点击事件
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_collect) {
            startActivity(new Intent(this, CollectActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            // TODO: 2018/5/6 dialog提示
            SpUtil.setBoolean(GlobalConstant.IS_LOGIN, false);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 保存用户按返回键的时间
    private long mExitTime = 0;

    /**
     * 根据当前时间节点判断是否退出
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.showToast("再按一次退出 " + getResources().getString(R.string.app_name));
                mExitTime = System.currentTimeMillis();
            } else {
                ActivityUtil.closeAllActivity();
            }
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
