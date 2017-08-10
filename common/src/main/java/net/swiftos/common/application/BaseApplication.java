package net.swiftos.common.application;

import android.Manifest;
import android.app.Application;

import com.jude.utils.JActivityManager;
import com.jude.utils.JUtils;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import net.swiftos.common.di.builder.ComponentBuilder;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.di.component.DaggerAppComponent;
import net.swiftos.common.di.module.AppModule;
import net.swiftos.common.log.SwiftLog;
import net.swiftos.common.model.net.OkHttpDownLoader;
import net.swiftos.common.ospermission.PermissionCheck;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.user.di.UserManagerComponent;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.presenter.Presenter;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by gy939 on 2017/1/11.
 */

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    private static boolean inited = false;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        init(setMainPresenter());
    }

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    protected void init(Class<? extends BasePresenter> presenter) {
        if (inited) return;
        inited = true;
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        ComponentManager.registerStaticComponent(AppComponent.class, appComponent);
        EventPoster.init(this);
        if (presenter != null) {
            Presenter.establish();
            Presenter.With(null).start(presenter);
        }
        JUtils.initialize(this);
        registerActivityLifecycleCallbacks(JActivityManager.getActivityLifecycleCallbacks());
        if (setComponentsBuilderMap() != null) {
            for (Class map:setComponentsBuilderMap()) {
                ComponentBuilder.addBuildMap(map);
            }
        }
    }

    public static void init(Class<? extends BasePresenter> presenter, Application app) {
        if (inited) return;
        inited = true;
        application = app;
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(app))
                .build();
        ComponentManager.registerStaticComponent(AppComponent.class, appComponent);
        EventPoster.init(app);
        Presenter.establish();
        Presenter.With(null).start(presenter);
        JUtils.initialize(app);
        app.registerActivityLifecycleCallbacks(JActivityManager.getActivityLifecycleCallbacks());
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    //初始化 Picasso
    private static void initPicasso() {
        Picasso.Builder picassoBuilder = new Picasso.Builder(BaseApplication.getApplication());
        Cache cache = new Cache(new File(BaseApplication.getApplication().getCacheDir(), "picasso"), 64 * 1024 * 1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        picassoBuilder.downloader(new OkHttpDownLoader(okHttpClient));
        picassoBuilder.memoryCache(new LruCache(15 * 1024 * 1024));
        try {
            Picasso picasso = picassoBuilder.build();
            //该方法只能调用一次
            Picasso.setSingletonInstance(picasso);
        } catch (Exception e) {
            SwiftLog.LOGE("PicassoLoader", "picasso init error");
        }
    }

    protected Class[] setComponentsBuilderMap() {
        return null;
    }

    protected Class<? extends BasePresenter> setMainPresenter() {
        return null;
    }

}
