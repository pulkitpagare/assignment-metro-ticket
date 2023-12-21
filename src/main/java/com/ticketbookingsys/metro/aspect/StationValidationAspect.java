package com.ticketbookingsys.metro.aspect;

import com.ticketbookingsys.metro.entity.STATION_TYPE;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class StationValidationAspect {

    /*
     * TODO : price restrictions on Stations
     *  accept a new parameter stationType
     *  if stationType are of this category (LUX, SEMI-LUX) then price should be minimum 20
     *   if stationType are of this category (NON-LUX) then price should be maximum 50
     * */

    @Before(value = "@annotation(com.ticketbookingsys.metro.annotation.StationValidation)")
    public void beforeAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(object -> {
            if (object != null) {
                STATION_TYPE stationType = null;
                Long price = null;
                Field[] fields =  object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = null;
                    try {
                        fieldValue = field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    switch (fieldName) {
                        case "price" -> price = (Long) fieldValue;
                        case "stationType" -> stationType = (STATION_TYPE) fieldValue;
                        default -> { }
                    }
                }
                if(stationType.equals(STATION_TYPE.LUX) || stationType.equals(STATION_TYPE.SEMI_LUX)) {
                    if(price < 20) throw new Error("For " + stationType + " Station, the price should be at least 20.");
                }
                if(stationType.equals(STATION_TYPE.NON_LUX)) {
                    if(price < 50) throw new Error("For " + stationType + " Station, the price should be at least 50.");
                }
            }
        });
    }

}
