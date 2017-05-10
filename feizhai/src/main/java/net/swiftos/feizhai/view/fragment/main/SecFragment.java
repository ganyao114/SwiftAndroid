package net.swiftos.feizhai.view.fragment.main;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.view.fragment.BaseFragment;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.model.bean.ArticleInfo;
import net.swiftos.feizhai.model.bean.Topic;
import net.swiftos.feizhai.protocol.HomeProtocol;
import net.swiftos.feizhai.view.activity.TopicActivity;
import net.swiftos.feizhai.view.adapter.ArticleListAdapter;
import net.swiftos.feizhai.view.adapter.TopicListAdapter;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.LoadMoreRecyclerView;
import net.swiftos.view.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/4/9.
 */

public class SecFragment extends BaseFragment<HomeProtocol.Presenter> implements LoadMoreRecyclerView.LoadMoreListener, OnItemClickListener {

    @Bind(R.id.topic_list)
    LoadMoreRecyclerView topicList;

    private CommonAdapter<Topic> topicsAdapter;
    private ArrayList<Topic> topics;

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        initArticles();
    }

    private void initArticles() {
        if (topicsAdapter == null) {
            topics = new ArrayList<>();
            for (int i = 0;i < 15; i++) {
                topics.add(new Topic());
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            topicsAdapter = new TopicListAdapter(getContext(), R.layout.item_topic_list, topics);
            topicsAdapter.setOnItemClickListener(this);
            layoutManager.setAutoMeasureEnabled(true);
            topicList.setLayoutManager(layoutManager);
            topicList.setHasFixedSize(true);
            topicList.setNestedScrollingEnabled(false);
            topicList.setAutoLoadMoreEnable(true);
            topicList.setLoadMoreListener(this);
            topicList.setAdapter(topicsAdapter);
        }
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_topics;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
    }

    @Override
    public void onLoadMore(View view) {

    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        startActivity(new Intent(getContext(), TopicActivity.class));
    }

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
        return false;
    }
}
