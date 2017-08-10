package net.swiftos.common.di.builder;

import android.util.Log;

import net.swiftos.eventposter.utils.LOG;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ganyao on 2017/8/10.
 */

public class ComponentBuilder {

    private static Map<Class,Method> builders = new ConcurrentHashMap<>();

    public static <T> Object build(Object target) {
        Class type = target.getClass();
        Method builderMethod = builders.get(type);
        if (builderMethod == null) {
            for (Class key:builders.keySet()) {
                if (key.isInstance(target)) {
                    builderMethod = builders.get(key);
                    break;
                }
            }
        }
        if (builderMethod == null) {
            return null;
        }
        T component = null;
        try {
            component = (T) builderMethod.invoke(null, target);
        } catch (Exception e) {
            return null;
        }
        return component;
    }

    public static <T> Object inject(Object target) {
        Class type = target.getClass();
        Method builderMethod = builders.get(type);
        Class parType = null;
        if (builderMethod == null) {
            for (Class key:builders.keySet()) {
                if (key.isInstance(target)) {
                    builderMethod = builders.get(key);
                    parType = key;
                    break;
                }
            }
        } else {
            parType = type;
        }
        if (builderMethod == null) {
            return null;
        }
        T component = null;
        try {
            component = (T) builderMethod.invoke(null, target);
        } catch (Exception e) {
            return null;
        }
        Method inject = null;
        try {
            inject = component.getClass().getDeclaredMethod("inject", parType);
        } catch (Exception e) {
            Log.e("ComponentBuilder: ", "lazy search fail");
        }
        if (inject == null) {
            for (Method method:component.getClass().getDeclaredMethods()) {
                Class[] pars = method.getParameterTypes();
                if (pars == null || pars.length != 1)
                    continue;
                if (pars[0].isInstance(target)) {
                    inject = method;
                    break;
                }
            }
        }
        if (inject != null) {
            try {
                inject.invoke(component, target);
            } catch (Exception e) {
                Log.e("ComponentBuilder: ", "inject error!");
                e.printStackTrace();
            }
        }
        return component;
    }

    public static void addBuildMap(Class map) {
        for (Method method:map.getDeclaredMethods()) {
            Class[] parTypes = method.getParameterTypes();
            if (parTypes.length != 1 || !Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            builders.put(parTypes[0], method);
        }
    }

}
