package net.swiftos.common.di.module;

import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.cache.IKVDiskCache;
import net.swiftos.common.cache.IKVRamCache;
import net.swiftos.common.event.IEventHub;
import net.swiftos.common.model.net.IBaseHttpModel;
import net.swiftos.common.model.net.IImageLoader;
import net.swiftos.common.presenter.IAsyncSubjectsQueue;


/**
 * App 基础组建依赖组装
 * Created by ganyao on 2017/3/15.
 */

public interface IAppModule {

    IKVRamCache provideKVRamCache();

    IKVDiskCache provideKVDiskCache();

    IAsyncSubjectsQueue provideSubscriber();

    IAPIGenerator provideAPIGenerator();

    IBaseHttpModel provideBaseHttpModel();

    IImageLoader provideImageLoader();

    IEventHub provideEventHub();
}
