package net.swiftos.apiservice.di.module;

import net.swiftos.apiservice.api.ICommonAPI;
import net.swiftos.common.di.module.BaseAPIModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/9.
 */
@Module
public class CommonAPIModule extends BaseAPIModule {

    @Provides
    @Singleton
    public ICommonAPI provideCommonAPI() {
        return getAPIService(ICommonAPI.class);
    }

}
