package net.swiftos.feizhai.presenter.home;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;
import net.swiftos.common.model.entity.BaseAsyncCallback;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.eventposter.core.Injecter;
import net.swiftos.feizhai.model.bean.Article;
import net.swiftos.feizhai.protocol.HomeProtocol;

import java.util.List;

/**
 * Created by ganyao on 2017/4/26.
 */

public class HomePresenter extends BasePresenter implements HomeProtocol.Presenter {

    HomeProtocol.Model model;
    HomeProtocol.View view;

    public HomePresenter(HomeProtocol.Model model, HomeProtocol.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onViewInited() {
    }

    @Override
    public <T> void onNavigate(T data) {

    }

    @Override
    public void hotArticles(int page, int lastId) {
        model.hotArticles(lastId, new BaseAsyncCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> articles) {
                HomeProtocol.View.SubViewFirst view = getAttachedView(HomeProtocol.View.SubViewFirst.class);
                if (view != null) {
                    view.showHotArticles((Integer) getTag(), articles);
                }
            }

            @Override
            public void onDone(Object tag) {
                HomeProtocol.View.SubViewFirst view = getAttachedView(HomeProtocol.View.SubViewFirst.class);
                if (view != null) {
                    view.dismissProgress();
                }
            }

            @Override
            public Object getTag() {
                return page;
            }

            @Override
            public void onError(ErrorResponse error) {
                HomeProtocol.View.SubViewFirst view = getAttachedView(HomeProtocol.View.SubViewFirst.class);
                if (view != null) {
                    view.showMessage("error!");
                }
            }

            @Override
            public void onFailure(FailureEntity failure) {
                HomeProtocol.View.SubViewFirst view = getAttachedView(HomeProtocol.View.SubViewFirst.class);
                if (view != null) {
                    view.showMessage("failure!");
                }
            }
        });
    }
}
