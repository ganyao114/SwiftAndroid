package net.swiftos.feizhai.di.module;

/**
 * Created by gy939 on 2017/1/16.
 */

import net.swiftos.apiservice.api.IUserAPI;
import net.swiftos.common.di.module.BaseAPIModule;
import net.swiftos.common.di.scope.UserScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserAPIServiceModule extends BaseAPIModule {

    public UserAPIServiceModule() {
        init("www.swiftos.net");
    }

    @UserScope
    @Provides
    public IUserAPI provideUserAPI() {
        return getRetrofit().create(IUserAPI.class);
    }

}
