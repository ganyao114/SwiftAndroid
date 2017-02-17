package net.swiftos.common.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 业务 生命周期
 * Created by ganyao on 2016/10/26.
 */
@Scope
@Retention(RUNTIME)
public @interface BusinessScope {
}
