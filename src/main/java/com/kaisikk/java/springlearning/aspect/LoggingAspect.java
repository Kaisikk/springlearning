package com.kaisikk.java.springlearning.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(Loggable)")
    public void executeLogging() {

    }

    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint) {
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName()).append("!");
        Arrays.stream(joinPoint.getArgs()).forEach(arg ->
                message.append("args").append(arg).append("!")
        );

        log.info(message.toString());
    }

    @AfterReturning(pointcut = "executeLogging()", returning = "returnValue")
    public void logMethodCall(JoinPoint joinPoint, Object returnValue) {

        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName()).append("!");

        message.append("return: " + returnValue);

        log.info(message.toString());
    }

    @Around("executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object returnValue = joinPoint.proceed();

        StringBuilder message = new StringBuilder("Method: ");

        message.append(joinPoint.getSignature().getName()).append("!");

        long spendTime = System.currentTimeMillis() - startTime;

        message.append("time:" + spendTime + " milliseconds");

        log.info(message.toString());

        return returnValue;
    }

}
