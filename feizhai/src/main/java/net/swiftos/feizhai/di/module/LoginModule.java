package net.swiftos.feizhai.di.module;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.eventposter.presenter.Presenter;
import net.swiftos.feizhai.model.net.LoginModel;
import net.swiftos.feizhai.presenter.LoginPresenter;
import net.swiftos.feizhai.presenter.MainPresenter;
import net.swiftos.feizhai.protocol.LoginProtocol;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/14.
 */
@Module
public class LoginModule {

    private LoginProtocol.View view;
    private LoginProtocol.Presenter presenter;
    private LoginProtocol.Model model;

    public LoginModule(LoginProtocol.View view) {
        this.view = view;
        this.model = new LoginModel();
        this.presenter = new LoginPresenter(model, view);
    }

    @Provides
    @ActivityScope
    public LoginProtocol.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    public LoginProtocol.Presenter providePresenter() {
        return presenter;
    }

    @Provides
    @ActivityScope
    public LoginProtocol.Model provideModel() {
        return model;
    }

}
