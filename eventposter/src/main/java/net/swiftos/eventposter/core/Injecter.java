package net.swiftos.eventposter.core;

import net.swiftos.eventposter.cache.EventCache;
import net.swiftos.eventposter.cache.ReflectionCache;
import net.swiftos.eventposter.entity.ClassDependTree;
import net.swiftos.eventposter.entity.EventAnnoInfo;
import net.swiftos.eventposter.factory.HandlerFactory;
import net.swiftos.eventposter.template.IEventEntity;
import net.swiftos.eventposter.template.IHandler;
import net.swiftos.eventposter.presenter.Presenter;
import net.swiftos.eventposter.reflect.parse.ClassParser;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gy939 on 2016/10/3.
 */
public class Injecter {

    private static Map<Class,Vector> instMap = new ConcurrentHashMap<>();
    private static ClassDependTree classTree = new ClassDependTree();

    public static void inject(Object object){
        Class clazz = object.getClass();
        if (instMap.get(clazz) == null) {
            synchronized (clazz) {
                if (instMap.get(clazz) == null) {
                    instMap.put(clazz, new Vector());
                }
            }
        }
        load(object,clazz);
        instMap.get(clazz).add(object);
    }

    public static void injectDeep(Object object){
        Class clazz = object.getClass();
        if (instMap.get(clazz) == null) {
            synchronized (clazz) {
                if (instMap.get(clazz) == null) {
                    instMap.put(clazz, new Vector());
                }
            }
        }
        loadDeep(object,clazz);
        instMap.get(clazz).add(object);
    }


    public static void remove(Object object){
        Class clazz = object.getClass();
        synchronized (clazz) {
            Vector vector = instMap.get(object.getClass());
            if (vector == null)
                return;
            vector.remove(object);
            dispatchRemove(clazz,object);
        }
    }

    public static void removeDeep(Object object){
        Class clazz = object.getClass();
        synchronized (clazz) {
            Vector vector = instMap.get(object.getClass());
            if (vector == null)
                return;
            vector.remove(object);
            Class<?> template = clazz;
            while (template != null && template != Object.class) {
                // 过滤掉基类 因为基类是不包含注解的
                String clazzName = template.getName();
                if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                        || clazzName.startsWith("android.") || clazz.equals(Presenter.class)) {
                    break;
                }
                dispatchRemove(template,object);
                template = template.getSuperclass();
            }
        }
    }

    private static void dispatchRemove(Class clazz,Object object) {
        Method[] methods = ReflectionCache.getMethods(clazz);
        if (methods == null)
            return;
        Map<Class<? extends IHandler>,IHandler> handlerMap = new HashMap<>();
        for (Method method:methods){
            EventAnnoInfo[] infos = ReflectionCache.getAnnoInfo(method);
            if (infos == null) continue;
            for (EventAnnoInfo eventAnnoInfo:infos){
                IHandler handler = HandlerFactory.getHandler(eventAnnoInfo.getHandler());
                IEventEntity eventEntity = EventCache.getEventEntity(eventAnnoInfo.getAnnotation());
                if (handler == null) continue;
                handler.unload(eventEntity,object);
                if (!handlerMap.containsKey(eventAnnoInfo.getHandler()))
                    handlerMap.put(eventAnnoInfo.getHandler(),handler);
            }
        }
        for (Map.Entry<Class<? extends IHandler>,IHandler> entry:handlerMap.entrySet()){
            entry.getValue().remove(object);
        }
    }

    public static Vector getInsts(Class clazz){
        return instMap.get(clazz);
    }

    public static Vector getInstsWithDirectChildren(Class clazz){
        List<Class> children = classTree.getDirectChildren(clazz);
        Vector insts = new Vector();
        if (insts != null)
            insts.addAll(instMap.get(clazz));
        if (children == null)
            return insts;
        for (Class child:children){
            Vector childinsts = instMap.get(child);
            if (childinsts != null)
                insts.addAll(childinsts);
        }
        return insts;
    }

    public static Vector getAllInsts(Class clazz){
        List<Class> children = classTree.getAllChildren(clazz,null);
        Vector insts = new Vector();
        Vector directInsts = instMap.get(clazz);
        if (directInsts != null)
            insts.addAll(directInsts);
        if (children == null)
            return insts;
        for (Class child:children){
            Vector childinsts = instMap.get(child);
            if (childinsts != null)
                insts.addAll(childinsts);
        }
        return insts;
    }

    public static ClassDependTree getClassTree(){
        return classTree;
    }

    public static void loadDeep(Object object,Class clazz){
        classTree.insertDeep(clazz);
        Class<?> template = clazz;
        while (template != null && template != Object.class) {
            // 过滤掉基类 因为基类是不包含注解的
            String clazzName = template.getName();
            if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                    || clazzName.startsWith("android.") || clazz.equals(Presenter.class)) {
                break;
            }
            load(object, template);
            template = template.getSuperclass();
        }
    }

    public static void load(Object object,Class clazz){
        synchronized (clazz) {
            Method[] methods = ClassParser.getMethods(clazz);
            if (methods == null)
                return;
            Map<Class<? extends IHandler>,IHandler> handlerMap = new HashMap<>();
            for (Method method : methods) {
                EventAnnoInfo[] eventAnnoInfos = ReflectionCache.getAnnoInfo(method);
                if (eventAnnoInfos == null || eventAnnoInfos.length == 0) continue;
                for (EventAnnoInfo eventAnnoInfo : eventAnnoInfos) {
                    IEventEntity eventEntity = EventCache.getEventEntity(eventAnnoInfo.getAnnotation());
                    Class<? extends IHandler> handlerType = eventAnnoInfo.getHandler();
                    IHandler handler = HandlerFactory.getHandler(handlerType);
                    if (eventEntity == null){
                        if (handler == null) continue;
                        eventEntity = handler.parse(eventAnnoInfo);
                        if (eventEntity == null) continue;
                        EventCache.addEventEntity(eventAnnoInfo.getAnnotation(), eventEntity);
                    }
                    if (object == null) continue;
                    handler.load(eventEntity,object);
                    if (!handlerMap.containsKey(eventAnnoInfo.getHandler())) {
                        handlerMap.put(eventAnnoInfo.getHandler(), handler);
                    }
                }
            }
            if (object == null)
                return;
            for (Map.Entry<Class<? extends IHandler>,IHandler> entry:handlerMap.entrySet()){
                entry.getValue().inject(object);
            }
        }
    }

}
