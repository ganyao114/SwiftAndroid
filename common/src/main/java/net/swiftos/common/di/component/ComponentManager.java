package net.swiftos.common.di.component;

import com.alibaba.android.arouter.launcher.ARouter;

import net.swiftos.common.di.builder.ComponentBuilder;
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
    private static Map<Object,Object> components = new ConcurrentHashMap<>();

    private static Map<String,Class> typeMap = new ConcurrentHashMap<>();

    /**
     * 不影响 Dagger2 生命周期管理
     */
    private static Map<Class,WeakReference> weakComponents = new ConcurrentHashMap<>();
    static {
        wrappers.put(UserManagerComponent.class, new Wrapper(UserManagerComponent.class, UserManager::init));
    }

    public static <T> void registerStaticComponent(Class type, T instance) {
        components.put(type, instance);
        AppComponent appComponent = getStaticComponent(AppComponent.class);
        if (appComponent != null) {
            appComponent.eventHub()
                    .postSticky(new ComponentEvent(ComponentEvent.Type.Register, type));
        }
    }

    public static <T> void registerStaticComponent(Class type, T instance, String key) {
        components.put(type, instance);
        typeMap.put(key, type);
        AppComponent appComponent = getStaticComponent(AppComponent.class);
        if (appComponent != null) {
            appComponent.eventHub()
                    .postSticky(new ComponentEvent(ComponentEvent.Type.Register, type));
        }
    }

    public static <T> T getStaticComponent(Class<T> type) {
        synchronized (type) {
            T t = (T) components.get(type);
            if (t == null) {
                if (wrappers.containsKey(type)) {
                    t = (T) wrappers.get(type).initer.init();
                    components.put(type, t);
                } else {
                    t = ComponentBuilder.build(type);
                    if (t != null) {
                        components.put(type, t);
                    }
                }
            }
            return t;
        }
    }

    public static <T> T getStaticComponent(String key) {
        Class type = typeMap.get(key);
        if (type == null)
            return null;
        return (T) getStaticComponent(type);
    }

    public static <T> T initComponent(Class<T> type) {
        synchronized (type) {
            return ARouter.getInstance().navigation(type);
        }
    }

    public static <T> T initComponent(String name) {
        return (T) ARouter.getInstance().build(name).navigation();
    }

    public static void removeStaticComponent(Class type) {
        Object o = components.remove(type);
        if (o != null) {
            AppComponent appComponent = getStaticComponent(AppComponent.class);
            if (appComponent != null) {
                appComponent.eventHub()
                        .postSticky(new ComponentEvent(ComponentEvent.Type.Register, type));
            }
        }
    }

    public static <T> void registerWeakComponent(Class type, T instance) {
        components.put(type, new WeakReference(instance));
        AppComponent appComponent = getStaticComponent(AppComponent.class);
        if (appComponent != null) {
            appComponent.eventHub()
                    .postSticky(new ComponentEvent(ComponentEvent.Type.Register, type));
        }
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
