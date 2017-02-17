package net.swiftos.eventposter.impls.customevent.annotation;

import net.swiftos.eventposter.annotation.EventBase;
import net.swiftos.eventposter.impls.customevent.entity.RunContextType;
import net.swiftos.eventposter.impls.customevent.handler.CustomEventHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gy939 on 2016/10/5.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(CustomEventHandler.class)
public @interface InjectEvent {
    String name() default "";
    RunContextType runType() default RunContextType.CurrentThread;
    int delay() default 0;
    boolean sticky() default false;
}
