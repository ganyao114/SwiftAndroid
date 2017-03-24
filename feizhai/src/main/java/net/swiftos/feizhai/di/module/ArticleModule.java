package net.swiftos.feizhai.di.module;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.model.net.LoginModel;
import net.swiftos.feizhai.presenter.LoginPresenter;
import net.swiftos.feizhai.protocol.ArticleProtocol;
import net.swiftos.feizhai.protocol.LoginProtocol;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/14.
 */
@Module
public class ArticleModule {

    private ArticleProtocol.View view;
    private ArticleProtocol.Presenter presenter;
    private ArticleProtocol.Model model;

    public ArticleModule(ArticleProtocol.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public ArticleProtocol.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    public ArticleProtocol.Presenter providePresenter() {
        return presenter;
    }

    @Provides
    @ActivityScope
    public ArticleProtocol.Model provideModel() {
        return model;
    }

}
