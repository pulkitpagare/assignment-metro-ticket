package com.ticketbookingsys.metro.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class CustomLogExecutionAspect {

    @Before(value = "@annotation(com.ticketbookingsys.metro.annotation.CustomLogExecution)")
    public void beforeExecution(JoinPoint joinPoint) throws Throwable {
        final String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        final String methodName = joinPoint.getSignature().getName();
        log.info("Entered {} in {} with params: {}", methodName, className, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "@annotation(com.ticketbookingsys.metro.annotation.CustomLogExecution)", returning = "result")
    public void afterExecution(JoinPoint joinPoint, Object result) throws Throwable {
        final String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        final String methodName = joinPoint.getSignature().getName();
        log.info("Exiting {} in {} with response: {}", methodName, className, result);
    }
}
