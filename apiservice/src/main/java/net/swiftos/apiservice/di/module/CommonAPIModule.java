package net.swiftos.apiservice.di.module;

import net.swiftos.apiservice.api.ICommonAPI;
import net.swiftos.common.di.module.BaseAPIModule;
import net.swiftos.common.di.scope.ServiceScope;
import net.swiftos.common.model.entity.Session;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ganyao on 2017/3/9.
 */
@Module
public class CommonAPIModule extends BaseAPIModule {

    public CommonAPIModule(String url) {
        init(url);
    }

    @Provides
    @ServiceScope
    public ICommonAPI provideCommonAPI() {
        return getAPIService(ICommonAPI.class);
    }

    @Provides
    @ServiceScope
    public Session provideSession() {
        return generateSession();
    }

}
