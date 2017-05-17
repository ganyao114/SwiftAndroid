package net.swiftos.feizhai.di.module;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.model.net.LoginModel;
import net.swiftos.feizhai.model.net.RegisterModel;
import net.swiftos.feizhai.presenter.LoginPresenter;
import net.swiftos.feizhai.presenter.RegisterPresenter;
import net.swiftos.feizhai.protocol.LoginProtocol;
import net.swiftos.feizhai.protocol.RegisterProtocol;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/14.
 */
@Module
public class RegisterModule {

    private RegisterProtocol.View view;
    private RegisterProtocol.Presenter presenter;
    private RegisterProtocol.Model model;

    public RegisterModule(RegisterProtocol.View view) {
        this.view = view;
        this.model = new RegisterModel();
        this.presenter = new RegisterPresenter(view, model);
    }

    @Provides
    @ActivityScope
    public RegisterProtocol.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    public RegisterProtocol.Presenter providePresenter() {
        return presenter;
    }

    @Provides
    @ActivityScope
    public RegisterProtocol.Model provideModel() {
        return model;
    }

}
