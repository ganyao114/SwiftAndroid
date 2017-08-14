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

    public static int S = 1000;
    public static int M = 60 * S;
    public static int H = 60 * M;
    public static int D = 24 * H;

    int keyPosition() default NO_KEY;
    CacheType type() default RamAndDisk;
    long expireRam() default NO_EXPIRE;
    long expireDisk() default NO_EXPIRE;
}