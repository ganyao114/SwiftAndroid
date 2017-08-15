package net.swiftos.common.di.builder;

import android.util.Log;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ganyao on 2017/8/10.
 */

public class ComponentBuilder {

    private static Map<Class,Method> injectors = new ConcurrentHashMap<>();
    private static Map<Class,Method> builders = new ConcurrentHashMap<>();

    public static <T> Object generate(Object target) {
        Class type = target.getClass();
        Method builderMethod = injectors.get(type);
        if (builderMethod == null) {
            for (Class key: injectors.keySet()) {
                if (key.isInstance(target)) {
                    builderMethod = injectors.get(key);
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

    public static <T> T build(Class<T> componentType) {
        Method builderMethod = builders.get(componentType);
        if (builderMethod == null) {
            return null;
        }
        T component = null;
        try {
            component = (T) builderMethod.invoke(null);
        } catch (Exception e) {
            return null;
        }
        return component;
    }

    public static <T> Object inject(Object target) {
        Class type = target.getClass();
        Method builderMethod = injectors.get(type);
        Class parType = null;
        if (builderMethod == null) {
            for (Class key: injectors.keySet()) {
                if (key.isInstance(target)) {
                    builderMethod = injectors.get(key);
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
        if (component == null)
            return null;
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


    public static void injectOnly(Object component, Object target) {
        Method inject = null;
        Class parType = target.getClass();
        try {
            inject = component.getClass().getDeclaredMethod("inject", parType);
        } catch (Exception e) {
            Log.v("ComponentBuilder: ", "lazy search fail");
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
    }

    public static void addBuildMap(Class map) {
        for (Method method:map.getDeclaredMethods()) {
            Class[] parTypes = method.getParameterTypes();
            if (!Modifier.isStatic(method.getModifiers()))
                continue;
            if (parTypes.length == 1) {
                injectors.put(parTypes[0], method);
            } else if (parTypes.length == 0) {
                Class componentType = method.getReturnType();
                if (componentType != null) {
                    builders.put(componentType, method);
                }
            }
        }
    }

}
