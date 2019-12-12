package com.yechaoa.wanandroidclient.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yechao on 2017/12/21.
 * Describe :
 */

public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> mFragments = new ArrayList<>();

    public CommonViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        title = titles;
    }

    public CommonViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
