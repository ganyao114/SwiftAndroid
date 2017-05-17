package net.swiftos.common.user.aop;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.log.SwiftLog;
import net.swiftos.common.ospermission.PermissionCheck;
import net.swiftos.common.user.UserManager;
import net.swiftos.common.user.di.UserManagerComponent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class LoginCheckAspect {

    @Pointcut("within(@net.swiftos.common.user.aop.LoginRequired *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@net.swiftos.common.user.aop.LoginRequired * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@net.swiftos.common.user.aop.LoginRequired *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Pointcut("execution(@net.swiftos.common.user.aop.LoginRequired * *(..)) && @annotation(loginRequired)")
    public void pointcutOnLoginCheckMethod(LoginRequired loginRequired) {

    }

    @Around("pointcutOnLoginCheckMethod(loginRequired)")
    public Object loginAndExecute(ProceedingJoinPoint joinPoint, LoginRequired loginRequired) throws Throwable {
        UserManager userManager = ComponentManager.getStaticComponent(UserManagerComponent.class).userManager();
        BaseApplication.getAppComponent()
                .eventHub()
                .post(new LoginChecked(userManager.isLogin() ? LoginChecked.LoginCheckedResult.Login : LoginChecked.LoginCheckedResult.UnLogin
                        , loginRequired.value()));
        if (userManager.isLogin()) {
            return joinPoint.proceed();
        } else {
            Object[] pars = joinPoint.getArgs();
            if (pars != null && pars.length > 0) {
                for (Object par:pars) {
                    if (par != null && par instanceof UnLoginCallback) {
                        UnLoginCallback callback = (UnLoginCallback) par;
                        callback.onUnLogined();
                        break;
                    }
                }
            }
            SwiftLog.LOGV("LoginManager", "please login first");
            return null;
        }
    }
}
