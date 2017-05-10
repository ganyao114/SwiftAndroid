package net.swiftos.common.di.component;

import net.swiftos.common.user.UserManager;
import net.swiftos.common.user.di.UserManagerComponent;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Global Component Manager
 * Created by ganyao on 2017/3/16.
 */

public class ComponentManager {

    private static Map<Class, Wrapper> wrappers = new ConcurrentHashMap<>();
    /**
     * lifecycle: static
     */
    private static Map<Class,Object> components = new ConcurrentHashMap<>();

    /**
     * 不影响 Dagger2 生命周期管理
     */
    private static Map<Class,WeakReference> weakComponents = new ConcurrentHashMap<>();
    static {
        wrappers.put(UserManagerComponent.class, new Wrapper(UserManagerComponent.class, UserManager::init));
    }

    public static <T> void registerStaticComponent(Class type, T instance) {
        components.put(type, instance);
    }

    public static <T> T getStaticComponent(Class<T> type) {
        synchronized (type) {
            T t = (T) components.get(type);
            if (t == null && wrappers.containsKey(type)) {
                t = (T) wrappers.get(type).initer.init();
                components.put(type, t);
            }
            return t;
        }
    }

    public static void removeStaticComponent(Class type) {
        components.remove(type);
    }

    public static <T> void registerWeakComponent(Class type, T instance) {
        components.put(type, new WeakReference(instance));
    }

    public static <T> T getWeakComponent(Class<T> type) {
        T t = (T) components.get(type);
        if (t == null)
            removeWeakComponent(type);
        return t;
    }

    public static void removeWeakComponent(Class type) {
        components.remove(type);
    }

    public static class Wrapper {

        public Class type;
        public ComponentIniter initer;

        public Wrapper(Class type, ComponentIniter initer) {
            this.type = type;
            this.initer = initer;
        }
    }
}
