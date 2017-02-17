package net.swiftos.eventposter.impls.broadcastreceiver.annotation;

import net.swiftos.eventposter.annotation.EventBase;
import net.swiftos.eventposter.impls.broadcastreceiver.handler.BroadCastReceiverHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gy939 on 2016/10/3.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(BroadCastReceiverHandler.class)
public @interface BroadCastReceiver {
    String[] value();
}
