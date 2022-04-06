package com.voytenko.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Date;

@Component
@Aspect
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("execution(* com.voytenko..*.*.*(..))")
    public void logExecutionTime() {

    }

    @Pointcut("@annotation(Loggable)")
    public void logGetNameCity() {

    }

    @Around("logGetNameCity()")
    public Object logCityName(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object cityName = proceedingJoinPoint.getArgs()[0];
        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("This city is requested: {}", cityName);

        return result;
    }

    @Around("logExecutionTime()")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getName();
        Object result = proceedingJoinPoint.proceed();

        LOGGER.info("At {} a request was made to the {} class", new Date(),  className);

        return result;
    }
}
