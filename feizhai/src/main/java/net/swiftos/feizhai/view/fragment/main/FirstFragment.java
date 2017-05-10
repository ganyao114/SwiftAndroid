package net.swiftos.feizhai.view.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.view.fragment.BaseFragment;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.model.bean.ArticleInfo;
import net.swiftos.feizhai.protocol.HomeProtocol;
import net.swiftos.feizhai.view.adapter.ArticleListAdapter;
import net.swiftos.view.banner.ADInfo;
import net.swiftos.view.banner.ImageCycleView;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.widget.NestedScrollView;

import butterknife.Bind;

/**
 * Created by ganyao on 2017/4/9.
 */

public class FirstFragment extends BaseFragment<HomeProtocol.Presenter> implements LoadMoreRecyclerView.LoadMoreListener {

    @Bind(R.id.loop_view)
    ImageCycleView cycleView;
    @Bind(R.id.main_article_list)
    LoadMoreRecyclerView articleList;
    @Bind(R.id.home_main_scroll)
    NestedScrollView scrollView;

    private List<ArticleInfo> articleInfos;

    private CommonAdapter<ArticleInfo> articlesAdapter;

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        initAds();
        initArticles();
    }

    private void initAds() {
        ArrayList<ADInfo> ads = new ArrayList();
        for (int i = 0;i < 5;i++) {
            ADInfo adInfo = new ADInfo();
            adInfo.setUrl("http://tupian.qqjay.com/u/2014/1117/23_174031_5.jpg");
            ads.add(adInfo);
        }
        cycleView.setImageResources(ads, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                ComponentManager.getStaticComponent(AppComponent.class)
                        .imageLoader()
                        .context(getContext())
                        .load(imageURL, imageView);
            }

            @Override
            public void onImageClick(ADInfo info, int position, View imageView) {

            }
        });
    }

    private void initArticles() {
        if (articlesAdapter == null) {
            articleInfos = new ArrayList<>();
            for (int i = 0;i < 15; i++) {
                articleInfos.add(new ArticleInfo());
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            articlesAdapter = new ArticleListAdapter(getContext(), R.layout.item_topic, articleInfos);
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
    protected int setLayoutId() {
        return R.layout.fragment_homemain_layout;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
    }

    @Override
    public void onLoadMore(View view) {

    }
}
