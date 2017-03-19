package net.swiftos.common.presenter;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.navigation.Navigater;
import net.swiftos.eventposter.presenter.Presenter;

/**
 * Created by ganyao on 2016/10/26.
 */

public abstract class BasePresenter extends Presenter implements Navigater.INavigate, IAsyncSubjectsQueue {

    protected IAsyncSubjectsQueue asyncSubjects;

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

    public void onViewInited() {

    }

    public void onViewDestroyed() {
        destroyQueue();
    }


}
