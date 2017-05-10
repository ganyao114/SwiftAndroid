package net.swiftos.feizhai.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.di.component.TopicComponent;
import net.swiftos.feizhai.protocol.TopicProtocol;
import net.swiftos.view.recyclerview.LoadMoreRecyclerView;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/4/27.
 */

public class TopicActivity extends BaseRefreshActivity<TopicComponent> {

    private TopicProtocol.Presenter presenter;

    Toolbar toolbar;

    @Bind(R.id.article_list)
    LoadMoreRecyclerView articleList;

    @Override
    protected void initView() {
        super.initView();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onRefresh(SwipeRefreshLayout swipeRefreshLayout) {

    }

    @Override
    protected int setRefreshLayoutId() {
        return R.id.topic_refresh_layout;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_topic;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int barColor() {
        return R.color.primary;
    }

    @Override
    protected TopicComponent setupActivityComponent() {
        return null;
    }

    @Override
    protected BasePresenter setPresenter() {
        return null;
    }
}
