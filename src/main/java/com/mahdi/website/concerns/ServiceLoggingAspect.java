package com.mahdi.website.concerns;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

@Aspect
@Component
@RequiredArgsConstructor
public class ServiceLoggingAspect {

    private final LogHandler logHandler;
    private final String packageName = "Service";
    private static final Logger logger = Logger.getLogger(ServiceLoggingAspect.class.getName());
    private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(ServiceLoggingAspect.class);


    @Pointcut("execution(* com.mahdi.website.service..*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, null, null);
        logger.info(stringBuilder.toString());
        logHandler.mangoDBLoggSaver(new Date(), "INFO", stringBuilder.toString());
        slf4jLogger.info("A service method is about to execute.");
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void loggAfterCreateUser(JoinPoint joinPoint, Object result) {
        if (Objects.nonNull(result)) {
            StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, result.toString(),  null);
            logger.info(stringBuilder.toString());
            logHandler.mangoDBLoggSaver(new Date(), "INFO", stringBuilder.toString());
        }
    }

    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, null, null);
        logger.info(stringBuilder.toString());
        logHandler.mangoDBLoggSaver(new Date(), "INFO", stringBuilder.toString());
        slf4jLogger.info("A service method has executed.");
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowingCreateUser(JoinPoint joinPoint, Exception ex){
        StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, null, ex);
        logger.info(stringBuilder.toString());
        logHandler.mangoDBLoggSaver(new Date(), "ERROR", stringBuilder.toString());
        slf4jLogger.error("An exception occurred in a service method: {}", ex.getMessage());
    }
}
