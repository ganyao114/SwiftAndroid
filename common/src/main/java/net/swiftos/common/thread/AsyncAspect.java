package net.swiftos.common.thread;

import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.ospermission.PermissionCheck;
import net.swiftos.eventposter.utils.LOG;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class AsyncAspect {

    @Pointcut("within(@net.swiftos.common.thread.Async *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@net.swiftos.common.thread.Async * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@net.swiftos.common.thread.Async *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Pointcut("execution(@net.swiftos.common.thread.Async * *(..)) && @annotation(async)")
    public void pointcutOnAsyncMethod(Async async) {

    }

    @Around("pointcutOnAsyncMethod(async)")
    public Object asyncAndExecute(ProceedingJoinPoint joinPoint, Async async) throws Throwable {

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
