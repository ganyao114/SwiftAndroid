package net.swiftos.eventposter.entity;

import net.swiftos.eventposter.presenter.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 构建 Class 继承关系
 * Created by ganyao on 2016/10/20.
 */

public class ClassDependTree {

    private Map<Class,ClassEntity> tree = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addClassDeep(Class clazz){
        try {
            lock.writeLock().lock();
            if (tree.containsKey(clazz)) return;
            Class<?> template = clazz;
            while (template != null && template != Object.class) {
                // 过滤掉基类 因为基类是不包含注解的
                String clazzName = template.getName();
                if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                        || clazzName.startsWith("android.") || clazz.equals(Presenter.class)) {
                    break;
                }
                insert(template);
                template = template.getSuperclass();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void addClass(Class clazz){
        try {
            lock.writeLock().lock();
            if (tree.containsKey(clazz)) return;
            insert(clazz);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public synchronized void insertDeep(Class clazz){
        insertDeep(clazz,null);
    }


    //递归一下
    private void insertDeep(Class clazz,Class child){
        String clazzName = clazz.getName();
        if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                || clazzName.startsWith("android.") || clazz.equals(Presenter.class)
                || clazz.equals(Object.class))
            return;
        ClassEntity entity = tree.get(clazz);
        if (entity == null){
            entity = new ClassEntity();
            tree.put(clazz,entity);
            entity.setSelf(clazz);
            entity.setParent(clazz.getSuperclass());
            entity.setInterface(clazz.isInterface());
            entity.setInterfaces(clazz.getInterfaces());
            if (child != null)
                entity.addChild(child);
            if (entity.isInterface()) {
                if (entity.getInterfaces() != null && entity.getInterfaces().length > 0) {
                    for (Class inter : entity.getInterfaces()) {
                        insertDeep(inter, clazz);
                    }
                }
            } else {
                insertDeep(clazz.getSuperclass(), clazz);
            }
        } else {
            if (child != null)
                entity.addChild(child);
        }
    }

    public List<Class> getDirectChildren(Class clazz){
        ClassEntity entity = tree.get(clazz);
        if (entity == null){
            return null;
        } else {
            return entity.getChildren();
        }
    }

    public List<Class> getAllChildren(Class clazz,List<Class> allchild){
        ClassEntity entity = tree.get(clazz);
        if (entity == null){
            return allchild;
        } else {
            if (allchild == null) allchild = new ArrayList<>();
            List<Class> directChildren = entity.getChildren();
            if (directChildren == null) return allchild;
            allchild.addAll(directChildren);
            for (Class directChild:directChildren){
                getAllChildren(directChild,allchild);
            }
            return allchild;
        }
    }

    public List<Class> getAllChildren(Class clazz){
        return getAllChildren(clazz, new ArrayList<>());
    }

    public List<Class> getAllImpls(Class clazz) {
        if (!clazz.isInterface())
            return null;
        List<Class> childrenInter = getAllChildren(clazz);
        List<Class> inters = new ArrayList<>();
        inters.add(clazz);
        if (childrenInter != null) {
            inters.addAll(childrenInter);
        }

        Set<Class> rawClasses = new HashSet<>();
        for (ClassEntity entity:tree.values()) {
            if (entity.isInterface())
                continue;
            if (entity.getInterfaces() == null || entity.getInterfaces().length == 0)
                continue;
            for (Class inter:entity.getInterfaces()) {
                if (inters.contains(inter)) {
                    rawClasses.add(entity.getSelf());
                    break;
                }
            }
        }

        Set<Class> tarClasses = new HashSet<>();

        tarClasses.addAll(rawClasses);

        for (Class raw:rawClasses) {
            List<Class> children = getAllChildren(raw);
            if (children == null || children.size() == 0)
                continue;
            tarClasses.addAll(children);
        }

        return new ArrayList<>(tarClasses);
    }

    private void insert(Class clazz){
        ClassEntity entity = new ClassEntity();
        Class parent = clazz.getSuperclass();
        entity.setSelf(clazz);
        entity.setParent(parent);
        tree.put(clazz,entity);
        ClassEntity parentEntity  = tree.get(parent);
        if (parentEntity == null){
            parentEntity = new ClassEntity();
            tree.put(parent,parentEntity);
        }
        parentEntity.addChild(clazz);
    }

}
