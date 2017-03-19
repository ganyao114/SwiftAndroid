package net.swiftos.common.di.component;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Global Component Manager
 * Created by ganyao on 2017/3/16.
 */

public class ComponentManager {

    /**
     * lifecycle: static
     */
    private static Map<String,Object> components = new ConcurrentHashMap<>();

    /**
     * 不影响 Dagger2 生命周期管理
     */
    private static Map<String,WeakReference> weakComponents = new ConcurrentHashMap<>();

    public static <T> void addStaticComponent(String name, T instance) {
        components.put(name, instance);
    }

    public static <T> T getStaticComponent(String name) {
        return (T) components.get(name);
    }

    public static void removeStaticComponent(String name) {
        components.remove(name);
    }

    public static <T> void addWeakComponent(String name, T instance) {
        components.put(name, new WeakReference(instance));
    }

    public static <T> T getWeakComponent(String name) {
        T t = (T) components.get(name);
        if (t == null)
            removeWeakComponent(name);
        return t;
    }

    public static void removeWeakComponent(String name) {
        components.remove(name);
    }


}
