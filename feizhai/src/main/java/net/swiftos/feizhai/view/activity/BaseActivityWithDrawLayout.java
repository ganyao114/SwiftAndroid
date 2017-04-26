package net.swiftos.feizhai.view.activity;

import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import net.swiftos.common.view.activity.BaseActivity;

import java.util.List;

/**
 * Created by ganyao on 2017/4/7.
 */

public abstract class BaseActivityWithDrawLayout<T> extends BaseFeizhaiActivity<T> implements NavigationView.OnNavigationItemSelectedListener
        , DrawerLayout.DrawerListener {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected boolean isOpen = false;

    @Override
    protected void initView() {
        drawerLayout = (DrawerLayout) findViewById(drawerLayoutId());
        navigationView = (NavigationView) findViewById(navigationViewId());
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(this);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        isOpen = true;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        isOpen = false;
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    protected abstract @IdRes int drawerLayoutId();

    protected abstract @IdRes int navigationViewId();

}
