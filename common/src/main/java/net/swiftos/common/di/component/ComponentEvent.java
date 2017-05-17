package net.swiftos.common.di.component;

import net.swiftos.common.event.BaseCustomerEvent;

/**
 * Created by ganyao on 2017/5/17.
 */

public class ComponentEvent extends BaseCustomerEvent {

    private Type type;

    public ComponentEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        Register,
        Remove
    }
}
