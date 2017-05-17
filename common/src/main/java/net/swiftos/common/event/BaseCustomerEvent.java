package net.swiftos.common.event;

/**
 * Created by ganyao on 2017/5/17.
 */

public abstract class BaseCustomerEvent {

    private boolean isStashed = false;

    public void stash() {
        isStashed = true;
    }

    public boolean isStashed() {
        return isStashed;
    }
}
