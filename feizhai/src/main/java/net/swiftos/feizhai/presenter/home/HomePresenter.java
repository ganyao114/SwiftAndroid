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
        hotArticles(0, 0);
    }

    @Override
    public <T> void onNavigate(T data) {

    }

    @Override
    public void hotArticles(int page, int lastId) {
        model.hotArticles(lastId, new BaseAsyncCallback<List<Article>>() {
            @Override
            public void onSuccess(List<Article> articles) {

            }

            @Override
            public void onDone(Object tag) {

            }

            @Override
            public Object getTag() {
                return page;
            }

            @Override
            public void onError(ErrorResponse error) {

            }

            @Override
            public void onFailure(FailureEntity failure) {

            }
        });
    }
}
