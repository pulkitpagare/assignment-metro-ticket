package com.ticketbookingsys.metro.aspect;

import lombok.Lombok;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before(value = "execution(* com.ticketbookingsys.metro.rest.TicketController.*(..))")
    public void beforeCallingMethod(JoinPoint joinPoint){
        System.out.println("Request to " + joinPoint.getSignature()+ "started at"+ new Date());
    }

//    @Around(value = "execution(* com.ticketbookingsys.metro.*.*.*(..))")
    @Around("@annotation(com.ticketbookingsys.metro.annotation.MyAnnotation)")
    public Object aroundMethodCalled(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] proceedingArgs = proceedingJoinPoint.getArgs();
        log.info("Params : ");
        for (Object o:proceedingArgs) {
            log.info(o + " ");
        }
        Object proceed = proceedingJoinPoint.proceed();
        log.info( "Response : " + proceed );
        return  proceed;
    }
}
