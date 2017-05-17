package net.swiftos.common.protocol;

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
    }
    public interface Presenter extends IAsyncSubjectsQueue {
        void onViewInited();
        void onViewDestroyed();
    }
    public interface Model {
        void destroyRequests();
    }
}
