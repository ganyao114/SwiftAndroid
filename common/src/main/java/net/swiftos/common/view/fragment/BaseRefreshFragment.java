package net.swiftos.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.swiftos.common.protocol.BaseProtocol;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by ganyao on 2017/8/16.
 */

public abstract class BaseRefreshFragment<T> extends BaseFragment<T> implements BaseProtocol.ProgressView
        , SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout refreshLayout;

    @Override
    public void dismissProgress() {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showProgress() {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
        refreshLayout = view.findViewById(refreshViewId());
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(this);
        }
    }

    protected abstract @IdRes int refreshViewId();
}
