package net.swiftos.common.di.component;

import net.swiftos.common.event.BaseCustomerEvent;

/**
 * Created by ganyao on 2017/5/17.
 */

public class ComponentEvent extends BaseCustomerEvent {

    private Type eventType;
    private Class<?> componentType;

    public ComponentEvent(Type eventType, Class<?> componentType) {
        this.eventType = eventType;
        this.componentType = componentType;
    }

    public Class<?> getComponentType() {
        return componentType;
    }

    public void setComponentType(Class<?> componentType) {
        this.componentType = componentType;
    }

    public Type getEventType() {
        return eventType;
    }

    public void setEventType(Type eventType) {
        this.eventType = eventType;
    }

    public enum Type {
        Register,
        Remove
    }
}
