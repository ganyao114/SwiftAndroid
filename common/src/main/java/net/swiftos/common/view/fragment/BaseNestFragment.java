package net.swiftos.common.view.fragment;

import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganyao on 2017/4/8.
 */

public abstract class BaseNestFragment<T> extends BaseFragment<T> {

    TabLayout main_tab;
    ViewPager main_viewpager;

    List<BaseFragment<T>> fragments;
    List<String> titles;
    MyAdapter adapter;


    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
        init();
    }

    private void init() {
        main_tab = (TabLayout) view.findViewById(setTabLayoutId());
        main_viewpager = (ViewPager) view.findViewById(setViewPagerId());
        fragments = setFragments();
        titles = setTitles();
        adapter = new MyAdapter(getChildFragmentManager());
        adapter.notifyDataSetChanged();
        main_viewpager.setAdapter(adapter);
        main_viewpager.setOffscreenPageLimit(fragments.size());
        main_tab.setTabMode(TabLayout.MODE_FIXED);
        main_tab.setupWithViewPager(main_viewpager);
    }

    public void addFragment(String title, BaseFragment<T> fragment) {
        titles.add(title);
        fragments.add(fragment);
        adapter.notifyDataSetChanged();
        main_viewpager.setOffscreenPageLimit(fragments.size());
    }

    protected abstract @IdRes int setTabLayoutId();

    protected abstract @IdRes int setViewPagerId();

    protected abstract List<BaseFragment<T>> setFragments();

    protected abstract List<String> setTitles();

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

}
