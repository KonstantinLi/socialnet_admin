package ru.skillbox.adminpanel.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcuts {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || " +
            "within(@org.springframework.stereotype.Repository *) || " +
            "within(@org.springframework.stereotype.Component *) || " +
            "within(@org.springframework.stereotype.Service *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(ru.skillbox.adminpanel..*) && !within(ru.skillbox.adminpanel.security.JwtRequestFilter)")
    public void applicationPackagePointcut() {
    }
}
