package net.swiftos.common.aop.cache;

import android.util.Log;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.zzhoujay.richtext.ext.MD5;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.cache.IKVDiskCache;
import net.swiftos.common.cache.IKVRamCache;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.ospermission.PermissionCheck;
import net.swiftos.common.ospermission.PermissionDenyEvent;
import net.swiftos.eventposter.utils.LOG;
import net.swiftos.utils.EncryptUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class BindCacheAspect  {

    public static String CACHE_KEY_HEAD = "method_cache";

    IKVRamCache ramCache;
    IKVDiskCache diskCache;

    @Pointcut("within(@net.swiftos.common.aop.cache.BindCache *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@net.swiftos.common.aop.cache.BindCache * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@net.swiftos.common.aop.cache.BindCache *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Pointcut("execution(@net.swiftos.common.aop.cache.BindCache * *(..)) && @annotation(bindCache)")
    public void pointcutBindCacheMethod(BindCache bindCache) {

    }

    @Around("pointcutBindCacheMethod(bindCache)")
    public Object bindCacheAndExecute(ProceedingJoinPoint joinPoint, BindCache bindCache) throws Throwable {

        if (ramCache == null || diskCache == null) {
            synchronized (this) {
                if (ramCache == null) {
                    ramCache = ComponentManager.getStaticComponent(AppComponent.class).kvRamCache();
                }
                if (diskCache == null) {
                    diskCache = ComponentManager.getStaticComponent(AppComponent.class).kvDiskCache();
                }
            }
        }

//        boolean needRamCache = (bindCache.type() == CacheType.Ram) || (bindCache.type() == CacheType.RamAndDisk) ? true : false;
//        boolean needDiskCache = (bindCache.type() == CacheType.Disk) || (bindCache.type() == CacheType.RamAndDisk) ? true : false;

        String key = null;

        if (bindCache.keyPosition() == BindCache.NO_KEY) {
            key = CACHE_KEY_HEAD + joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName();
            key = EncryptUtils.MD5(key);
        } else {
            Object object = joinPoint.getArgs()[bindCache.keyPosition()];
            if (object != null) {
                key = CACHE_KEY_HEAD + joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName() + object.toString();
                key = EncryptUtils.MD5(key);
            }
        }

        Object res = null;

        if (key == null) {
            return joinPoint.proceed();
        }

        switch (bindCache.type()) {
            case Ram:
                if (ramCache == null)
                    return joinPoint.proceed();
                res = ramCache.get(key);
                if (res == null) {
                    res = joinPoint.proceed();
                    if (res != null) {
                        ramCache.save(key, res);
                    }
                }
                return res;
            case RamAndDisk:
                if (ramCache != null) {
                    res = ramCache.get(key);
                    if (res == null) {
                        if (diskCache == null) {
                            res = joinPoint.proceed();
                        } else {
                            res = diskCache.get(key);
                            if (res == null) {
                                res = joinPoint.proceed();
                                if (res != null) {
                                    if (bindCache.expire() == BindCache.NO_EXPIRE)
                                        diskCache.save(key, res);
                                    else
                                        diskCache.save(key, res, (int) bindCache.expire());
                                }
                            }
                        }
                        if (res != null) {
                            ramCache.save(key, res);
                        }
                    }
                    return res;
                }
            case Disk:
                if (diskCache == null)
                    return joinPoint.proceed();
                res = diskCache.get(key);
                if (res == null) {
                    res = joinPoint.proceed();
                    if (res != null) {
                        if (bindCache.expire() == BindCache.NO_EXPIRE)
                            diskCache.save(key, res);
                        else
                            diskCache.save(key, res, (int) bindCache.expire());
                    }
                }
                return res;
        }
        return joinPoint.proceed();
    }
}
