package net.swiftos.common.aop.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static net.swiftos.common.aop.cache.CacheType.RamAndDisk;

@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface BindCache {

    public static int NO_KEY = -1;
    public static int NO_EXPIRE = -1;

    int keyPosition() default NO_KEY;
    CacheType type() default RamAndDisk;
    long expire() default NO_EXPIRE;

}