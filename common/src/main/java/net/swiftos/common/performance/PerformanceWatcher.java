package net.swiftos.common.performance;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 监控方法的性能
 * Created by ganyao on 2017/4/5.
 */
@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface PerformanceWatcher {
    long value() default 0;
}
