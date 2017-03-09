package net.swiftos.common.presenter;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.navigation.Navigater;
import net.swiftos.eventposter.presenter.Presenter;

/**
 * Created by ganyao on 2016/10/26.
 */

public abstract class BasePresenter extends Presenter implements Navigater.INavigate{

    protected IAsyncSubjectsQueue asyncSubjects;

    //取消注册，以避免内存泄露
    public void destorySubjectsQueue() {
        if (asyncSubjects != null) {
            asyncSubjects.destroy();
        }
    }

    //注册观察者 callback
    public void submitSubject(IAsyncSubject observer) {
        if (asyncSubjects == null) {
            asyncSubjects = BaseApplication.getAppComponent().generateSubscriber();
        }
        asyncSubjects.addSubject(observer);
    }

    public void onViewInited() {

    }

    public void onViewDestoryed() {
        destorySubjectsQueue();
    }


}
