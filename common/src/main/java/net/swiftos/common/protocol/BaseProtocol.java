package net.swiftos.common.protocol;

import android.view.View;

import net.swiftos.common.model.entity.Session;
import net.swiftos.common.presenter.IAsyncSubjectsQueue;
import net.swiftos.eventposter.core.EventPoster;

/**
 * Created by ganyao on 2017/3/14.
 */

public class BaseProtocol {
    public interface ProgressView {
        void showProgress();
        void dismissProgress();
    }
    public interface View<T> {
        void showMessage(String string);
        void lockUI();
        void unLockUI();
        void finish();
        T getComponent();
        Object getParentComponent();
        <V extends android.view.View> V findView(int id);
    }
    public interface Presenter<C> extends IAsyncSubjectsQueue {
        void onViewInited();
        void onViewDestroyed();
        <T> void attachView(Class<T> viewType, T view);
        <T> void detachView(Class<T> viewType);
        void injectComponent(C component);
        C getComponent();
    }
    public interface Model {
        void destroyRequests();
    }
}
