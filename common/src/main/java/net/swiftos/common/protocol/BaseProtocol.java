package net.swiftos.common.protocol;

import net.swiftos.common.presenter.IAsyncSubjectsQueue;
import net.swiftos.eventposter.core.EventPoster;

/**
 * Created by ganyao on 2017/3/14.
 */

public class BaseProtocol {
    public interface View {
        void showMessage(String string);
        void lockUI();
        void unLockUI();
    }
    public interface Presenter extends IAsyncSubjectsQueue {
        void onViewInited();
        void onViewDestroyed();
    }
    public interface Model {

    }
}
