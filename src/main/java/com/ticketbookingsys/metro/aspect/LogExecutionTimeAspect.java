package com.ticketbookingsys.metro.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("@annotation(com.ticketbookingsys.metro.annotation.LogExecutionTime)")
    public Object afterMethodExecution(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        final long start = System.currentTimeMillis();
        final Object proceed = proceedingJoinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - start;
        final String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        final String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("{} : {} is executed in {} ms", className, methodName, executionTime);
        return proceed;
    }
}
