package ru.skillbox.adminpanel.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AnnotationPointcuts {
    @Pointcut("(@target(ru.skillbox.adminpanel.annotation.Info) || " +
            "@annotation(ru.skillbox.adminpanel.annotation.InfoLoggable)) &&" +
            "!@annotation(ru.skillbox.adminpanel.annotation.DebugLoggable) && " +
            "!@annotation(ru.skillbox.adminpanel.annotation.NotLoggable)")
    public void info() {
    }

    @Pointcut("(@target(ru.skillbox.adminpanel.annotation.Debug) || " +
            "@annotation(ru.skillbox.adminpanel.annotation.DebugLoggable)) &&" +
            "!@annotation(ru.skillbox.adminpanel.annotation.InfoLoggable) && " +
            "!@annotation(ru.skillbox.adminpanel.annotation.NotLoggable)")
    public void debug() {
    }
}
