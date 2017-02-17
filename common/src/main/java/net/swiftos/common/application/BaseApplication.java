package net.swiftos.common.application;

import android.app.Application;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.DaggerAppComponent;
import net.swiftos.common.di.module.AppModule;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.presenter.Presenter;

/**
 * Created by gy939 on 2017/1/11.
 */

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    private static BaseApplication application;

    public static BaseApplication getApplication() {
        return application;
    }

    protected void init(Class<? extends BasePresenter> presenter) {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        EventPoster.init(this);
        Presenter.establish();
        Presenter.With(null).start(presenter);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
