package net.swiftos.feizhai.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.view.fragment.BaseFragment;
import net.swiftos.common.view.fragment.BaseNestFragment;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.di.component.HomeComponent;
import net.swiftos.feizhai.protocol.HomeProtocol;
import net.swiftos.feizhai.view.fragment.main.FirstFragment;
import net.swiftos.feizhai.view.fragment.main.SecFragment;
import net.swiftos.view.banner.ADInfo;
import net.swiftos.view.banner.ImageCycleView;
import net.swiftos.view.recyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/4/9.
 */

public class MainFragment extends BaseNestFragment<HomeProtocol.Presenter> {


    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
        super.initView(inflater, container);
    }

    @Override
    protected int setTabLayoutId() {
        return R.id.home_tab;
    }

    @Override
    protected int setViewPagerId() {
        return R.id.home_viewPager;
    }

    @Override
    protected List<BaseFragment<HomeProtocol.Presenter>> setFragments() {
        List<BaseFragment<HomeProtocol.Presenter>> fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecFragment());
        return fragments;
    }

    @Override
    protected List<String> setTitles() {
        List<String> list = new ArrayList<>();
        list.add("主页");
        list.add("话题");
        return list;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_layout;
    }
}
