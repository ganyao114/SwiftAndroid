package net.swiftos.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;

/**
 * Created by ganyao on 2017/3/7.
 */

public abstract class BaseFragment<T> extends Fragment {

    /**
     * 懒加载控制
     */
    private boolean isLazyLoaded;
    private boolean isPrepared;
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = initView(inflater, container);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    protected T getPresenter() {
        return (T) ((BaseActivity)getActivity()).getPresenter();
    }

    protected void onLazyLoad(){

    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

}
