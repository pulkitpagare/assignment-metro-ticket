package com.ticketbookingsys.metro.aspect;

import com.ticketbookingsys.metro.entity.Type;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import lombok.Lombok;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
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
        log.info("Request to " + joinPoint.getSignature()+ "started at"+ new Date());
    }

//    @Around(value = "execution(* com.ticketbookingsys.metro.*.*.*(..))")
    @Around("@annotation(com.ticketbookingsys.metro.annotation.MyAnnotation)")
    public Object aroundMethodCalled(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] proceedingArgs = proceedingJoinPoint.getArgs();
        log.info("Params : ");
        Arrays.stream(proceedingArgs).forEach((obj) -> log.info(obj + " "));
        Object proceed = proceedingJoinPoint.proceed();
        log.info( "Response : " + proceed );
        return  proceed;
    }

//    @Around("@annotation(com.ticketbookingsys.metro.annotation.EnumValidationAnnotation)") will do it using annotation too and push it
    @Around(value = "execution(* com.ticketbookingsys.metro.rest.StationController.addStation(..)) && args(body)")
    public Object beforeAddingStation(ProceedingJoinPoint proceedingJoinPoint , CreateStationRequest body) throws Throwable {

        if(body.getStationType().equals(Type.LUX) || body.getStationType().equals(Type.SEMI_LUX) || body.getStationType().equals(Type.NON_LUX)){
            if(((body.getStationType().equals(Type.LUX) ||body.getStationType().equals(Type.SEMI_LUX)) && body.getPrice() >=20 )
                    || (body.getStationType().equals(Type.NON_LUX) && body.getPrice() <=50)
            ){
                return proceedingJoinPoint.proceed();
            }else{
                throw new Exception("please enter correct amount");
            }
        }else{
            throw new Exception("please enter correct station type");
        }

    }
}
