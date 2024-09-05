package com.example.cards.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
//    @Pointcut(value = "execution(* com.example.cards.service.concrete.CardServiceHandler.*(..))")
//    public void loggingAspectPointCut(){
//    }
    @SneakyThrows
    @Around("@within(Loggable) || @annotation(Loggable)")
    public Object loggingAspect(ProceedingJoinPoint jp){
        var signature = (MethodSignature) jp.getSignature();
        var method = signature.getMethod();
        if (method.isAnnotationPresent(LogIgnore.class)) {
            return jp.proceed();
        }
        var methodName = jp.getSignature().getName();
        var params = jp.getArgs();
        log.info("ActionLog.{}.start",methodName);
        log.info("ActionLog.{}.params: {}",methodName,params);
        var response = jp.proceed();
        log.info("ActionLog.{}.end",methodName);
        return response;
    }

}
