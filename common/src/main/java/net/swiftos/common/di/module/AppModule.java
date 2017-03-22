package net.swiftos.common.di.module;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.api.RetrofitAPIGenerator;
import net.swiftos.common.cache.IKVDiskCache;
import net.swiftos.common.cache.KVACacheImpl;
import net.swiftos.common.event.IEventHub;
import net.swiftos.common.model.net.IBaseHttpModel;
import net.swiftos.common.model.net.IImageLoader;
import net.swiftos.common.model.net.PicassoLoader;
import net.swiftos.common.model.net.RxBaseHttpModel;
import net.swiftos.common.presenter.IAsyncSubjectsQueue;
import net.swiftos.common.presenter.RxAsyncSubjectsQueue;

import org.afinal.simplecache.ACache;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2016/10/26.
 */
@Module
public class AppModule implements IAppModule {

    private Application globalApp;

    private IAppModule appModule;

    public AppModule(IAppModule appModule, Application globalApp) {
        this.appModule = appModule;
        this.globalApp = globalApp;
    }

    public AppModule(Application globalApp) {
        this.globalApp = globalApp;
        this.appModule = new DefaultAppModule();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return globalApp;
    }

    @Provides
    @Singleton
    public ConcurrentHashMap provideGlobalData() {
        return new ConcurrentHashMap<>();
    }

    @Provides
    @Singleton
    public Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Singleton
    public ACache provideACache() {
        return ACache.get(globalApp);
    }

    @Provides
    @Singleton
    public IKVDiskCache provideKVDiskCache() {
        return appModule.provideKVDiskCache();
    }

    @Provides
    public IAsyncSubjectsQueue provideSubscriber() {
        return appModule.provideSubscriber();
    }

    @Provides
    public IAPIGenerator provideAPIGenerator() {
        return appModule.provideAPIGenerator();
    }

    @Provides
    public IBaseHttpModel provideBaseHttpModel() {
        return appModule.provideBaseHttpModel();
    }

    @Provides
    @Singleton
    public IImageLoader provideImageLoader() {
        return appModule.provideImageLoader();
    }

    @Provides
    @Singleton
    public IEventHub provideEventHub() {
        return appModule.provideEventHub();
    }

}
