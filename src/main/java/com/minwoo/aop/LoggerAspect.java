package com.minwoo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;


@Slf4j
@Component
@Aspect
public class LoggerAspect {
    @Around("execution(* com.minwoo.domain..*Controller.*(..)) || execution(* com.minwoo.domain..*Service.*(..)) || execution(* com.minwoo.domain..*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws  Throwable {
        String name = joinPoint.getSignature().getDeclaringTypeName();
        String type =
                StringUtils.contains(name, "Controller") ? "Controller ===>" :
                StringUtils.contains(name, "service") ? "Service ===> " :
                StringUtils.contains(name, "Mapper") ? "Mapper ===>" : "";

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }
}
