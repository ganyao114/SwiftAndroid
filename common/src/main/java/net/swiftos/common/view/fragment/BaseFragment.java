package net.swiftos.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;

import butterknife.ButterKnife;

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
    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setLayoutId(), null);
            ButterKnife.bind(this, view);
            initView(inflater, container);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected T getPresenter() {
        return (T) ((BaseActivity)getActivity()).getPresenter();
    }

    protected void onLazyLoad(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(view);
    }

    protected abstract @IdRes int setLayoutId();

    protected abstract void initView(LayoutInflater inflater, ViewGroup container);

}
