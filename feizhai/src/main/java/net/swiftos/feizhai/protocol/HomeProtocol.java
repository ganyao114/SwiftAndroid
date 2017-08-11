package net.swiftos.feizhai.protocol;

import net.swiftos.common.model.entity.BaseAsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.common.presenter.IAsyncSubjectsQueue;
import net.swiftos.common.protocol.BaseProtocol;
import net.swiftos.feizhai.di.component.HomeComponent;
import net.swiftos.feizhai.model.bean.Article;

import java.util.List;

/**
 * Created by ganyao on 2017/3/15.
 */

public interface HomeProtocol {
    interface View extends BaseProtocol.View<HomeComponent> {
        void showHotArticles(int page, List<Article> articles);
        interface SubViewFirst extends BaseProtocol.View, BaseProtocol.ProgressView {
            void showHotArticles(int page, List<Article> articles);
        }
    }
    interface Presenter extends BaseProtocol.Presenter {
        void hotArticles(int page, int lastId);
    }
    interface Model {
        IAsyncSubject hotArticles(int lastId, BaseAsyncCallback<List<Article>> callback);
    }
}
