package net.swiftos.common.protocol;

/**
 * Created by ganyao on 2017/3/14.
 */

public class BaseProtocol {
    public interface View {
        void showMessage(String string);
        void lockUI();
        void unLockUI();
    }
    public interface Presenter{

    }
    public interface Model{

    }
}
