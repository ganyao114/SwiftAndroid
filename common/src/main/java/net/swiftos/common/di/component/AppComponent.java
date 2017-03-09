package net.swiftos.common.di.component;

import android.content.Context;
import android.os.Handler;

import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.cache.IKVDiskCache;
import net.swiftos.common.cache.KVACacheImpl;
import net.swiftos.common.di.module.AppModule;
import net.swiftos.common.model.net.BaseRxModel;
import net.swiftos.common.model.net.IBaseHttpModel;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.presenter.IAsyncSubjectsQueue;
import net.swiftos.common.view.activity.BaseActivity;

import org.afinal.simplecache.ACache;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App 全局依赖的容器
 * Created by ganyao on 2016/10/26.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(Object object);

    void inject(BaseActivity activity);

    void inject(BaseRxModel model);

    void inject(BasePresenter presenter);

    Context globalContext();

    ConcurrentHashMap globalData();

    IAPIGenerator apiGenerator();

    IKVDiskCache kvDiskCache();

    ACache aCache();

    Handler mainHandler();

    IAsyncSubjectsQueue generateSubscriber();

    IBaseHttpModel getBaseHttpModel();

}
