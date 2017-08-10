package net.swiftos.eventposter.modules.viewevent.annotation;

import android.view.View;

import net.swiftos.eventposter.annotation.EventBase;
import net.swiftos.eventposter.modules.viewevent.handler.ViewEventHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(ViewEventHandler.class)
@ViewEventBase(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick",viewType = View.class)
public @interface OnClick {
    String context() default ViewEventHandler.DEFAULT_CONTEXT;
    int[] viewIds();
}
