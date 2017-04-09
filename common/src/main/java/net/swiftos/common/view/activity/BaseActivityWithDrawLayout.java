package net.swiftos.common.view.activity;

import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/4/7.
 */

public abstract class BaseActivityWithDrawLayout<T> extends BaseActivity<T> implements NavigationView.OnNavigationItemSelectedListener
        , DrawerLayout.DrawerListener {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void initView() {
        drawerLayout = (DrawerLayout) findViewById(drawerLayoutId());
        navigationView = (NavigationView) findViewById(navigationViewId());
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(this);
    }

    protected abstract @IdRes int drawerLayoutId();

    protected abstract @IdRes int navigationViewId();


}
