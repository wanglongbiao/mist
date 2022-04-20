package com.mist.consumer.apo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AopAspect {

    @Before("this(com.mist.consumer.service.impl.ConsumerServiceImpl)")
    public void before() {
        log.info("before this...");
    }

    @SneakyThrows
    @Around("this(com.mist.consumer.service.impl.ConsumerServiceImpl)")
    public Object around(ProceedingJoinPoint pjp) {
        Signature signature = pjp.getSignature();
        String methodName = signature.getName();
        String longName = signature.toLongString();
        log.info("around {} {}", methodName, longName);
        Object proceed = pjp.proceed();
        log.info("after around");
        return proceed;
    }
}
