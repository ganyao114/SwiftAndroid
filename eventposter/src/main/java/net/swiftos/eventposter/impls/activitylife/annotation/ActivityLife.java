package net.swiftos.eventposter.impls.activitylife.annotation;

import android.app.Activity;

import net.swiftos.eventposter.annotation.EventBase;
import net.swiftos.eventposter.impls.activitylife.entity.ActivityLifeType;
import net.swiftos.eventposter.impls.activitylife.handler.ActivityLifeHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gy939 on 2016/9/26.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(ActivityLifeHandler.class)
public @interface ActivityLife {
    ActivityLifeType lifeType();
    Class<? extends Activity>[] activity();
}
