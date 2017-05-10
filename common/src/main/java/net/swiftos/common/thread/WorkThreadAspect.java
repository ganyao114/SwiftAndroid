package net.swiftos.common.thread;

import android.support.annotation.WorkerThread;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class WorkThreadAspect {

    @Pointcut("within(@android.support.annotation.WorkerThread *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@android.support.annotation.WorkerThread * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@android.support.annotation.WorkerThread *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Around("method() || constructor()")
    public Object asyncAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        new Thread( () -> {
            Object[] pars = joinPoint.getArgs();
            AsyncSuccessCallback successCallback = null;
            AsyncFailureCallback failureCallback = null;
            if (pars != null) {
                for (Object par : pars) {
                    if (par instanceof AsyncSuccessCallback) {
                        successCallback = (AsyncSuccessCallback) par;
                    } else if (par instanceof AsyncFailureCallback) {
                        failureCallback = (AsyncFailureCallback) par;
                    }
                }
            }
            try {
                Object res = joinPoint.proceed();
                if (successCallback != null) {
                    successCallback.success(res);
                }
            } catch (Throwable throwable) {
                if (failureCallback != null) {
                    failureCallback.failure(throwable);
                }
            }
        }).start();
        return null;
    }
}
