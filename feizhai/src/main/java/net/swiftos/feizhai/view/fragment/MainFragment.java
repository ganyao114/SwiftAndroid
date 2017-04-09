package net.swiftos.feizhai.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.swiftos.common.view.fragment.BaseFragment;
import net.swiftos.common.view.fragment.BaseNestFragment;
import net.swiftos.feizhai.di.component.HomeComponent;
import net.swiftos.feizhai.protocol.HomeProtocol;

import java.util.List;

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
        return 0;
    }

    @Override
    protected int setViewPagerId() {
        return 0;
    }

    @Override
    protected List<BaseFragment<HomeProtocol.Presenter>> setFragments() {
        return null;
    }

    @Override
    protected List<String> setTitles() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return 0;
    }
}
