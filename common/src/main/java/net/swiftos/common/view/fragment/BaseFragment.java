package net.swiftos.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.swiftos.common.di.builder.ComponentBuilder;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.protocol.BaseProtocol;
import net.swiftos.common.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by ganyao on 2017/3/7.
 */

public abstract class BaseFragment<T> extends Fragment implements BaseProtocol.View<T>, BaseProtocol.ProgressView {

    /**
     * 懒加载控制
     */
    private boolean isLazyLoaded;
    private boolean isPrepared;
    protected View view;
    protected String name;

    //自己的 presenter
    protected BasePresenter presenter;
    protected T component;

    //父 Component
    protected Object parentComponent;

    protected boolean isOwnPresenter = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setLayoutId(), null);
            ButterKnife.bind(this, view);
            initView(inflater, container);
            component = setComponent();
            if (component == null) {
                parentComponent = getParentComponent();
                if (parentComponent != null) {
                    ComponentBuilder.injectOnly(parentComponent, this);
                }
            }
            presenter = setPresenter();
            if (presenter == null) {
                presenter = getActivityPresenter();
                if (presenter != null) {
                    presenter.attachView(viewType(), this);
                }
            } else {
                isOwnPresenter = true;
                if (presenter instanceof BasePresenter) {
                    BasePresenter ownPresenter = presenter;
                    ownPresenter.onViewInited();
                }
            }
        }
        return view;
    }

    protected Class viewType() {
        return getClass();
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

    protected BasePresenter getActivityPresenter() {
        if (getActivity() instanceof  BaseActivity) {
            return ((BaseActivity) getActivity()).getPresenter();
        } else {
            return null;
        }
    }

    protected T getPresenter() {
        return (T) presenter;
    }

    protected BasePresenter getBasePresenter() {
        if (presenter == null || !(presenter instanceof BasePresenter)) {
            return null;
        } else {
            return (BasePresenter) presenter;
        }
    }

    protected void onLazyLoad(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(view);
        if (isOwnPresenter && presenter instanceof BasePresenter) {
            BasePresenter ownPresenter = (BasePresenter) presenter;
            ownPresenter.destroyQueue();
        } else if (presenter instanceof BasePresenter) {
            ((BasePresenter) presenter).detachView(viewType());
        }
    }

    public T getComponent() {
        return component;
    }

    @Override
    public Object getParentComponent() {
        if (getActivity() instanceof BaseProtocol.View) {
            BaseProtocol.View ac = (BaseProtocol.View) getActivity();
            return ac.getComponent();
        } else {
            return null;
        }
    }

    @Override
    public void showMessage(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lockUI() {

    }

    @Override
    public void unLockUI() {

    }

    @Override
    public void finish() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showProgress() {

    }

    protected abstract @IdRes int setLayoutId();

    protected abstract void initView(LayoutInflater inflater, ViewGroup container);

    protected T setComponent() {
        return (T) ComponentBuilder.inject(this);
    }

    protected BasePresenter setPresenter() {
        return null;
    }

}
