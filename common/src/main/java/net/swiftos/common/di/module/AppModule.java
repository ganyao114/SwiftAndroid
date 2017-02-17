package net.swiftos.common.di.module;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.afinal.simplecache.ACache;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2016/10/26.
 */
@Module
public class AppModule {

    private Application globalApp;

    public AppModule(Application globalApp) {
        this.globalApp = globalApp;
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

}
