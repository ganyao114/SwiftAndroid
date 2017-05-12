package net.swiftos.feizhai.view.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerTopicComponent;
import net.swiftos.feizhai.di.component.TopicComponent;
import net.swiftos.feizhai.di.module.TopicModule;
import net.swiftos.feizhai.model.bean.ArticleInfo;
import net.swiftos.feizhai.protocol.TopicProtocol;
import net.swiftos.feizhai.view.adapter.ArticleListAdapter;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/4/27.
 */

public class TopicActivity extends BaseRefreshActivity<TopicComponent> implements TopicProtocol.View, LoadMoreRecyclerView.LoadMoreListener {

    private TopicProtocol.Presenter presenter;

    Toolbar toolbar;

    @Bind(R.id.article_list)
    LoadMoreRecyclerView articleList;
    @Bind(R.id.topic_scroll)
    NestedScrollView scrollView;

    private List<ArticleInfo> articleInfos;

    private CommonAdapter<ArticleInfo> articlesAdapter;

    @Override
    protected void initView() {
        super.initView();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initArticles();
    }

    private void initArticles() {
        if (articlesAdapter == null) {
            articleInfos = new ArrayList<>();
            for (int i = 0;i < 15; i++) {
                articleInfos.add(new ArticleInfo());
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            articlesAdapter = new ArticleListAdapter(this, R.layout.item_topic, articleInfos);
            layoutManager.setAutoMeasureEnabled(true);
            articleList.setLayoutManager(layoutManager);
            articleList.setHasFixedSize(true);
            articleList.setNestedScrollingEnabled(false);
            articleList.setAutoLoadMoreEnable(true);
            articleList.setLoadMoreListener(this);
            articleList.setAdapter(articlesAdapter);
            ComponentManager.getStaticComponent(AppComponent.class)
                    .mainHandler()
                    .post( () -> scrollView.fullScroll(NestedScrollView.FOCUS_UP));
        }
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
        return DaggerTopicComponent.builder()
                .feiZhaiAPIComponent(ServiceManager.getFeiZhaiAPIComponent())
                .topicModule(new TopicModule(this))
                .build();
    }

    @Override
    protected BasePresenter setPresenter() {
        presenter = component.presenter();
        return (BasePresenter) presenter;
    }

    @Override
    public void onLoadMore(View view) {

    }
}
