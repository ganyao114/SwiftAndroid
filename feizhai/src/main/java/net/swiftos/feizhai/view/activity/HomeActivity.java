package net.swiftos.feizhai.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivityWithDrawLayout;
import net.swiftos.feizhai.di.component.HomeComponent;

/**
 * Created by ganyao on 2017/4/8.
 */

public class HomeActivity extends BaseActivityWithDrawLayout<HomeComponent> {

    ViewPager view_pager;
    TabLayout tabLayout;
    Toolbar toolbar;


    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected int getContentLayout() {
        return 0;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected int drawerLayoutId() {
        return 0;
    }

    @Override
    protected int navigationViewId() {
        return 0;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    protected int barColor() {
        return 0;
    }

    @Override
    protected HomeComponent setupActivityComponent() {
        return null;
    }

    @Override
    protected BasePresenter setPresenter() {
        return null;
    }
}
