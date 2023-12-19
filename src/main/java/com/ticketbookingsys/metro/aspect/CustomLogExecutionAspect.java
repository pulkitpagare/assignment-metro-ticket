package com.ticketbookingsys.metro.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CustomLogExecutionAspect {

    @Around(value = "@annotation(com.ticketbookingsys.metro.annotation.CustomLogExecution)")
    public Object aroundMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        final String methodName = proceedingJoinPoint.getSignature().getName();
        StringBuilder params = new StringBuilder();
        for(Object object : proceedingJoinPoint.getArgs()) {
            params.append(object).append(" ");
        }
        params = params.isEmpty() ? new StringBuilder("null") : params;
        log.info("Entered {} in {} with params: {}", methodName, className, params);
        final Object proceed = proceedingJoinPoint.proceed();
        log.info("Exiting {} in {} with response: {}", methodName, className, proceed);
        return proceed;
    }
}
