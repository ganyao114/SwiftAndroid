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
import net.swiftos.feizhai.model.bean.Article;
import net.swiftos.feizhai.model.bean.ArticleInfo;
import net.swiftos.feizhai.protocol.HomeProtocol;
import net.swiftos.feizhai.view.adapter.ArticleListAdapterDemo;
import net.swiftos.utils.ValidateUtil;
import net.swiftos.view.banner.ADInfo;
import net.swiftos.view.banner.ImageCycleView;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.widget.NestedScrollView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by ganyao on 2017/4/9.
 */

public class FirstFragment extends BaseFragment<HomeProtocol.Presenter>
        implements LoadMoreRecyclerView.LoadMoreListener
        , HomeProtocol.View.SubViewFirst, XRecyclerView.LoadingListener
        , SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.loop_view)
    ImageCycleView cycleView;
    @Bind(R.id.main_article_list)
    XRecyclerView articleList;
    @Bind(R.id.home_main_scroll)
    NestedScrollView scrollView;
    @Bind(R.id.main_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<ArticleInfo> articleInfos;

    private List<Article> articles = new ArrayList<>();

    private CommonAdapter<ArticleInfo> articlesAdapter;

    private int page = 0;

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        refreshLayout.setOnRefreshListener(this);
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
            articlesAdapter = new ArticleListAdapterDemo(getContext(), R.layout.item_topic, articleInfos);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            articleList.setLayoutManager(layoutManager);
            articleList.setHasFixedSize(false);
            articleList.setNestedScrollingEnabled(false);
            articleList.setLoadingMoreEnabled(false);
            articleList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
            articleList.setPullRefreshEnabled(false);
            articleList.setLoadingListener(this);
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
    protected Class viewType() {
        return HomeProtocol.View.SubViewFirst.class;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
    }

    @Override
    public void onLoadMore(View view) {
        int lastId = articles.size() == 0 ? 0
                : articles.get(articles.size() - 1).getId();
        presenter.hotArticles(page + 1, lastId);
    }

    @Override
    public void showHotArticles(int page, List<Article> articles) {
        if (!ValidateUtil.isEmpty(articles)) {
            this.page = page;
            int size = this.articles.size();
            int start = size == 0 ? 0 : size - 1;
            this.articles.addAll(articles);
            articleInfos.add(new ArticleInfo());
            articleList.loadMoreComplete();
            articleList.getAdapter().notifyDataSetChanged();
        } else {
            articleList.loadMoreComplete();
            articleList.setLoadingMoreEnabled(false);
            articleList.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        onLoadMore(view);
    }

    @Override
    public void showProgress() {
        refreshLayout.setEnabled(true);
    }

    @Override
    public void dismissProgress() {
        refreshLayout.setEnabled(false);
    }
}
