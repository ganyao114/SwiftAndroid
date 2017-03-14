package net.swiftos.feizhai.di.module;

import net.swiftos.common.di.module.BaseAPIModule;
import net.swiftos.common.di.scope.ServiceScope;
import net.swiftos.common.model.entity.Session;
import net.swiftos.feizhai.api.IFeiZhaiAPI;
import net.swiftos.feizhai.application.Constant;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2017/3/14.
 */

@Module
public class FeiZhaiAPIModule extends BaseAPIModule {


    public FeiZhaiAPIModule() {
        init(Constant.FEIZHAI_URL);
    }

    @Provides
    @ServiceScope
    public IFeiZhaiAPI provideFeizhaiAPI() {
        return apiGenerator.getAPI(IFeiZhaiAPI.class);
    }


    @Provides
    @ServiceScope
    public Session provideSession() {
        return generateSession();
    }

}
