package net.swiftos.feizhai.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;

import net.swiftos.common.view.IRefreshView;
import net.swiftos.common.view.activity.BaseActivity;

/**
 * Created by ganyao on 2017/4/12.
 */

public abstract class BaseRefreshActivity<T> extends BaseFeizhaiActivity<T> implements IRefreshView {

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(setRefreshLayoutId());
        swipeRefreshLayout.setOnRefreshListener(() -> BaseRefreshActivity.this.onRefresh(swipeRefreshLayout));
    }

    @Override
    public final boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    @Override
    public final boolean startRefresh() {
        if (isRefreshing()) {
            return false;
        }
        swipeRefreshLayout.setRefreshing(true);
        onRefresh(swipeRefreshLayout);
        return true;
    }

    @Override
    public final boolean finishRefresh() {
        if (!isRefreshing()) {
            return false;
        }
        swipeRefreshLayout.setRefreshing(false);
        return true;
    }

    @Override
    public final SwipeRefreshLayout getRefreshView() {
        return swipeRefreshLayout;
    }

    protected abstract void onRefresh(SwipeRefreshLayout swipeRefreshLayout);

    protected abstract @IdRes int setRefreshLayoutId();
}
