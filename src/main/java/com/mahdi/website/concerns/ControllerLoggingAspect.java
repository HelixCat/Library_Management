package com.mahdi.website.concerns;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

@Aspect
@Component
@RequiredArgsConstructor
public class ControllerLoggingAspect {

    private final LogHandler logHandler;
    private final String packageName = "Controller";
    private static final Logger logger = Logger.getLogger(ControllerLoggingAspect.class.getName());

    @Before("execution(* com.mahdi.website.service..*(..))")
    public void loggBeforeCreateUser(JoinPoint joinPoint) {
        StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, null, null);
        logger.info(stringBuilder.toString());
        logHandler.mangoDBLoggSaver(new Date(), "INFO", stringBuilder.toString());
    }

    @AfterReturning(pointcut = "execution(* com.mahdi.website.service..*(..))", returning = "result")
    public void loggAfterCreateUser(JoinPoint joinPoint, Object result) {
        if (Objects.nonNull(result)) {
            StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, result.toString(), null);
            logger.info(stringBuilder.toString());
            logHandler.mangoDBLoggSaver(new Date(), "INFO", stringBuilder.toString());
        }
    }

    @AfterThrowing(pointcut = "execution(* com.mahdi.website.service..*(..))", throwing = "ex")
    public void logAfterThrowingCreateUser(JoinPoint joinPoint, Exception ex){
        StringBuilder stringBuilder = logHandler.prepareLog(joinPoint, packageName, null, ex);
        logger.info(stringBuilder.toString());
        logHandler.mangoDBLoggSaver(new Date(), "ERROR", stringBuilder.toString());
    }

}
