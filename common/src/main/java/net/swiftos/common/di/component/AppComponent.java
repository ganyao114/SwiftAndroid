package net.swiftos.common.di.component;

import android.content.Context;
import android.os.Handler;

import net.swiftos.common.di.module.AppModule;
import net.swiftos.common.model.net.BaseModel;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;

import org.afinal.simplecache.ACache;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * App 全局依赖的容器
 * Created by ganyao on 2016/10/26.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(BaseActivity activity);

    void inject(BaseModel model);

    void inject(BasePresenter presenter);

    Context globalContext();

    ConcurrentHashMap globalData();

    ACache diskCache();

    Handler mainHandler();

}
