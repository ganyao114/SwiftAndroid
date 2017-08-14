package net.swiftos.common.di.module;

import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.api.RetrofitAPIGenerator;
import net.swiftos.common.cache.IKVDiskCache;
import net.swiftos.common.cache.IKVRamCache;
import net.swiftos.common.cache.KVACacheImpl;
import net.swiftos.common.cache.LruCacheWithExpire;
import net.swiftos.common.event.EventPosterHub;
import net.swiftos.common.event.IEventHub;
import net.swiftos.common.model.net.IBaseHttpModel;
import net.swiftos.common.model.net.IImageLoader;
import net.swiftos.common.model.net.PicassoLoader;
import net.swiftos.common.model.net.RxBaseHttpModel;
import net.swiftos.common.presenter.IAsyncSubjectsQueue;
import net.swiftos.common.presenter.RxAsyncSubjectsQueue;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by ganyao on 2017/3/15.
 */

public class DefaultAppModule implements IAppModule {

    @Override
    public IKVRamCache provideKVRamCache() {
        return new LruCacheWithExpire(1024);
    }

    @Override
    public IKVDiskCache provideKVDiskCache() {
        return new KVACacheImpl();
    }
    @Override
    public IAsyncSubjectsQueue provideSubscriber() {
        return new RxAsyncSubjectsQueue();
    }
    @Override
    public IAPIGenerator provideAPIGenerator() {
        return new RetrofitAPIGenerator();
    }

    @Override
    public IBaseHttpModel provideBaseHttpModel() {
        return new RxBaseHttpModel();
    }

    @Override
    public IImageLoader provideImageLoader() {
        return new PicassoLoader();
    }

    @Override
    public IEventHub provideEventHub() {
        return new EventPosterHub();
    }
}
