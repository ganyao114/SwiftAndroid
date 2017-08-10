package net.swiftos.feizhai.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.user.RegisteredEvent;
import net.swiftos.common.user.aop.LoginChecked;
import net.swiftos.common.user.aop.LoginRequired;
import net.swiftos.eventposter.modules.customevent.annotation.InjectEvent;
import net.swiftos.eventposter.modules.customevent.entity.RunContextType;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerHomeComponent;
import net.swiftos.feizhai.di.component.HomeComponent;
import net.swiftos.feizhai.di.module.HomeModule;
import net.swiftos.feizhai.model.bean.Article;
import net.swiftos.feizhai.protocol.HomeProtocol;
import net.swiftos.feizhai.view.fragment.MainFragment;

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
        header.setOnClickListener(this::navigateToUserPage);
    }

    private void initFragments() {
        fragments.add(new MainFragment());
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
    protected int barColor() {
        return R.color.primary;
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_login:
                break;
            case R.id.nav_search:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @LoginRequired
    public void navigateToUserPage(View view) {

    }

    @InjectEvent(runType = RunContextType.MainThread)
    public void onRegistered(RegisteredEvent event) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @InjectEvent(runType = RunContextType.MainThread)
    public void onUnloginChecked(LoginChecked event) {
        if (event.getResult() == LoginChecked.LoginCheckedResult.UnLogin) {
            showMessage("尚未登陆!");
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void showHotArticles(int page, List<Article> articles) {

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
