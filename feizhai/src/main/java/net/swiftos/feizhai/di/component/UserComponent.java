package net.swiftos.feizhai.di.component;

import net.swiftos.apiservice.api.IUserAPI;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.scope.UserScope;
import net.swiftos.feizhai.di.module.UserAPIServiceModule;
import net.swiftos.feizhai.model.net.UserModel;

import dagger.Component;

/**
 * Created by gy939 on 2017/1/16.
 */
@UserScope
@Component(modules = UserAPIServiceModule.class, dependencies = AppComponent.class)
public interface UserComponent {

    void inject(UserModel model);

    IUserAPI getAPI();

}
