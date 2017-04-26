package net.swiftos.feizhai.di.module;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.model.net.HomeModel;
import net.swiftos.feizhai.presenter.HomePresenter;
import net.swiftos.feizhai.protocol.HomeProtocol;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/15.
 */
@Module
public class HomeModule {

    private HomeProtocol.View view;
    private HomeProtocol.Presenter presenter;
    private HomeProtocol.Model model;

    public HomeModule(HomeProtocol.View view) {
        this.view = view;
        this.model = new HomeModel();
        this.presenter = new HomePresenter(model, view);
    }

    @Provides
    @ActivityScope
    public HomeProtocol.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    public HomeProtocol.Presenter providePresenter() {
        return presenter;
    }

    @Provides
    @ActivityScope
    public HomeProtocol.Model provideModel() {
        return model;
    }

}
