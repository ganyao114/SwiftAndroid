package net.swiftos.feizhai.di.module;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.model.net.TopicModel;
import net.swiftos.feizhai.presenter.TopicPresenter;
import net.swiftos.feizhai.protocol.ArticleProtocol;
import net.swiftos.feizhai.protocol.TopicProtocol;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/14.
 */
@Module
public class TopicModule {

    private TopicProtocol.View view;
    private TopicProtocol.Presenter presenter;
    private TopicProtocol.Model model;

    public TopicModule(TopicProtocol.View view) {
        this.view = view;
        this.model = new TopicModel();
        this.presenter = new TopicPresenter(view, model);
    }

    @Provides
    @ActivityScope
    public TopicProtocol.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    public TopicProtocol.Presenter providePresenter() {
        return presenter;
    }

    @Provides
    @ActivityScope
    public TopicProtocol.Model provideModel() {
        return model;
    }

}
