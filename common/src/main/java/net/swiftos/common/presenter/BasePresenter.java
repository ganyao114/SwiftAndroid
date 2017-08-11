package net.swiftos.common.presenter;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.navigation.Navigater;
import net.swiftos.common.protocol.BaseProtocol;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.presenter.Presenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ganyao on 2016/10/26.
 */

public abstract class BasePresenter extends Presenter implements Navigater.INavigate, BaseProtocol.Presenter {

    protected IAsyncSubjectsQueue asyncSubjects;

    protected Map<Class,Object> views = new ConcurrentHashMap<>();

    public BasePresenter() {
        BaseApplication.getAppComponent()
                .eventHub()
                .register(this);
    }

    //取消注册，以避免内存泄露
    @Override
    public void destroyQueue() {
        if (asyncSubjects != null) {
            asyncSubjects.destroyQueue();
        }
    }

    @Override
    public void removeSubject(IAsyncSubject observer) {
        if (asyncSubjects != null) {
            asyncSubjects.removeSubject(observer);
        }
    }

    //注册观察者 callback
    @Override
    public void addSubject(IAsyncSubject observer) {
        if (asyncSubjects == null) {
            asyncSubjects = BaseApplication.getAppComponent().generateSubscriber();
        }
        asyncSubjects.addSubject(observer);
    }

    @Override
    public void onViewInited() {

    }

    @Override
    public void onViewDestroyed() {
        destroyQueue();
        BaseApplication.getAppComponent()
                .eventHub()
                .unRegister(this);
    }

    @Override
    public <T> void attachView(Class<T> viewType, T view) {
        views.put(viewType, view);
        onViewAttached(viewType, view);
    }

    protected  <T> void onViewAttached(Class<T> viewType, T view) {

    }

    @Override
    public <T> void detachView(Class<T> viewType) {
        onViewDetached(viewType, views.remove(viewType));
    }

    protected  <T> void onViewDetached(Class<T> viewType, Object remove) {

    }

    public <T> T getAttachedView(Class<T> viewType) {
        return (T) views.get(viewType);
    }

    @Override
    public <T> void onNavigate(T data) {

    }
}
