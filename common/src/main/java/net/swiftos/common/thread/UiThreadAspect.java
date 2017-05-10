package net.swiftos.common.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class UiThreadAspect {

    @Pointcut("within(@android.support.annotation.UiThread *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@android.support.annotation.UiThread * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@android.support.annotation.UiThread *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Around("method() || constructor()")
    public Object asyncAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return joinPoint.proceed();
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                }
            });
            return null;
        }
    }
}
