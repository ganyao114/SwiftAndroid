package net.swiftos.eventposter.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ganyao on 2016/10/20.
 */

public class ClassEntity implements Cloneable{

    private Class self;
    private Class parent;
    private boolean isInterface = false;
    private Class[] interfaces;
    private List<Class> children = new CopyOnWriteArrayList<>();;

    public Class getSelf() {
        return self;
    }

    public void setSelf(Class self) {
        this.self = self;
    }

    public Class getParent() {
        return parent;
    }

    public void setParent(Class parent) {
        this.parent = parent;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public Class[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class[] interfaces) {
        this.interfaces = interfaces;
    }

    public List<Class> getChildren() {
        return children;
    }

    public void setChildren(List<Class> children) {
        this.children = children;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassEntity entity = (ClassEntity) super.clone();
        self = null;
        parent = null;
        children = null;
        return entity;
    }

    public void addChild(Class clazz) {
        children.add(clazz);
    }
}
