package net.swiftos.eventposter.impls.viewevent.annotation;

import android.view.View;

import net.swiftos.eventposter.annotation.EventBase;
import net.swiftos.eventposter.impls.viewevent.handler.ViewEventHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(ViewEventHandler.class)
@ViewEventBase(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick",viewType = View.class)
public @interface OnClick {
    String context();
    int[] viewIds();
}
