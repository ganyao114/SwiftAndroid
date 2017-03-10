package net.swiftos.common.ospermission;

import android.Manifest;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.log.SwiftLog;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.impls.customevent.handler.CustomEventHandler;
import net.swiftos.eventposter.utils.LOG;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by gy on 2017/2/27.
 */
@Aspect
public class PermissionCheckAspect {
    @Pointcut("within(@net.swiftos.common.ospermission.PermissionCheck *)")
    public void withinAnnotatedClass() {}

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}

    @Pointcut("execution(@net.swiftos.common.ospermission.PermissionCheck * *(..)) || methodInsideAnnotatedType()")
    public void method() {}

    @Pointcut("execution(@net.swiftos.common.ospermission.PermissionCheck *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}

    @Pointcut("execution(@net.swiftos.common.ospermission.PermissionCheck * *(..)) && @annotation(permissionCheck)")
    public void pointcutOnPermissionCheckMethod(PermissionCheck permissionCheck) {

    }

    @Around("pointcutOnPermissionCheckMethod(permissionCheck)")
    public Object permissionCheckAndExecute(ProceedingJoinPoint joinPoint, PermissionCheck permissionCheck) throws Throwable {

        RxPermissions.getInstance(BaseApplication.getApplication())
                .request(permissionCheck.value())
                .subscribe(granted -> {
                    if (granted) {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            LOG.e("permission check invoke error!");
                        }
                    } else {
                        Toast.makeText(BaseApplication.getApplication(), "请授予权限！", Toast.LENGTH_LONG).show();
                    }
                });

        return null;

    }
}
