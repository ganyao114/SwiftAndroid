package net.swiftos.eventposter.modules.customevent.entity;

/**
 * Created by pc on 16/5/13.
 */
public enum RunContextType {
    MainThread,
    MainLoop,
    NewThread,
    NewHandlerThread,
    CurrentThread,
    CurrentLoop,
    IO,
    Calculate,
    Dynamic,
    Custome;
}
