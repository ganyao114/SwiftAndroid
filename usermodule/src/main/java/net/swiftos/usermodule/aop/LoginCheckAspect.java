package net.swiftos.usermodule.aop;

import net.swiftos.common.log.SwiftLog;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.impls.customevent.handler.CustomEventHandler;
import net.swiftos.usermodule.UserManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class LoginCheckAspect {
    @Pointcut("within(@net.swiftos.usermodule.aop.LoginRequired *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@net.swiftos.usermodule.aop.LoginRequired * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@net.swiftos.usermodule.aop.LoginRequired *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Around("method() || constructor()")
    public Object loginAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {

        EventPoster.With(CustomEventHandler.class).broadcast(new LoginChecked(UserManager.isLogin() ? LoginChecked.LoginCheckedResult.Login : LoginChecked.LoginCheckedResult.UnLogin));

        if (UserManager.isLogin()) {
            return joinPoint.proceed();
        } else {
            SwiftLog.LOGV("LoginManager", "please login first");
            return null;
        }

    }
}
