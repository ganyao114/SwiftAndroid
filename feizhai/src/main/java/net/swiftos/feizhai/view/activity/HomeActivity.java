package net.swiftos.feizhai.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerHomeComponent;
import net.swiftos.feizhai.di.component.HomeComponent;
import net.swiftos.feizhai.di.module.HomeModule;
import net.swiftos.feizhai.protocol.HomeProtocol;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ganyao on 2017/4/8.
 */

public class HomeActivity extends BaseActivityWithDrawLayout<HomeComponent> implements HomeProtocol.View
        , ViewPager.OnPageChangeListener {

    public HomeProtocol.Presenter presenter;

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tool_bar)
    Toolbar toolbar;


    @Bind(R.id.bar_home)
    ImageView barHome;
    @Bind(R.id.bar_article)
    ImageView barArticle;
    @Bind(R.id.bar_my)
    ImageView barMy;

    private MyFragmentPagerAdapter adapter;
    protected List<Fragment> fragments = new ArrayList<>(3);

    private View header;
    private ImageView photo;
    private TextView name;
    private TextView tel;

    @Override
    protected void initView() {
        super.initView();
        initFragments();
        setSupportActionBar(toolbar);
        header = navigationView.getHeaderView(0);
        photo = (ImageView) header.findViewById(R.id.nav_photo);
        name = (TextView) header.findViewById(R.id.nav_name);
        tel = (TextView) header.findViewById(R.id.nav_tel);
    }

    private void initFragments() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        barHome.setSelected(true);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected int drawerLayoutId() {
        return R.id.drawer;
    }

    @Override
    protected int navigationViewId() {
        return R.id.navigation_view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected int barColor() {
        return R.color.primary;
    }

    @Override
    protected HomeComponent setupActivityComponent() {
        return DaggerHomeComponent.builder()
                .feiZhaiAPIComponent(ServiceManager.getFeiZhaiAPIComponent())
                .homeModule(new HomeModule(this))
                .build();
    }

    @Override
    protected BasePresenter setPresenter() {
        presenter = component.presenter();
        return (BasePresenter) presenter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.bar_home, R.id.bar_article, R.id.bar_my, R.id.drawerIcon})
    public void onBarClick(View view) {
        switch (view.getId()) {
            case R.id.drawerIcon:
                if (!isOpen) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                    isOpen = true;
                }
                break;
            case R.id.bar_home:
                break;
            default:
                break;
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
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
    }
}
