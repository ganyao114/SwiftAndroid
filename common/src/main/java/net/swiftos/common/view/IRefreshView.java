package net.swiftos.common.view;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by ganyao on 2017/4/12.
 */

public interface IRefreshView {
    boolean isRefreshing();
    boolean startRefresh();
    boolean finishRefresh();
    SwipeRefreshLayout getRefreshView();
}
