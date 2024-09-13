package com.mahdi.website.aspect;

import com.mahdi.website.model.Logg;
import com.mahdi.website.repository.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    private final LogRepository logRepository;

    @Autowired
    public LoggingAspect(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Before("execution(* com.mahdi.website.service.interfaces.UserServiceInterface.saveUser(..))")
    public void loggBeforeCreateUser(JoinPoint joinPoint) {
        logger.info("before executing method save user In the User Service");
        logger.info(joinPoint.getSignature().getName());
        logger.info("Arguments passed to the method { \n" + Arrays.toString(joinPoint.getArgs()) + " }");
        String message = "before executing method save user In the User Service \n " +
                "Arguments passed to the method { \n " + Arrays.toString(joinPoint.getArgs()) + " \n }";
        mangoDBLoggSaver(new Date(), "INFO", message);
    }

    @AfterReturning(pointcut = "execution(* com.mahdi.website.service.interfaces.UserServiceInterface.saveUser(..))", returning = "result")
    public void loggAfterCreateUser(JoinPoint joinPoint, Object result) {
        logger.info("after executing method save user In the User Service");
        logger.info(joinPoint.getSignature().getName());
        logger.info("Arguments passed to the method { \n" + Arrays.toString(joinPoint.getArgs()) + " }");
        String message = "after executing method save user In the User Service \n " +
                "Arguments passed to the method { \n " + result + " \n }";
        mangoDBLoggSaver(new Date(), "INFO", message);
    }

    @AfterThrowing(pointcut = "execution(* com.mahdi.website.service.interfaces.UserServiceInterface.saveUser(..))", throwing = "ex")
    public void logAfterThrowingCreateUser(JoinPoint joinPoint, Exception ex){
        logger.info("after throwing exception executing method save user In the User Service");
        logger.info(joinPoint.getSignature().getName());
        logger.info("Arguments passed to the method { \n" + Arrays.toString(joinPoint.getArgs()) + " }");
        String message = ex.getMessage();
        mangoDBLoggSaver(new Date(), "ERROR", message);
    }

    private void mangoDBLoggSaver(Date date, String level, String message) {
        Logg logg = new Logg();
        logg.setDate(date);
        logg.setLevel(level);
        logg.setMessage(message);
        logRepository.save(logg);
    }
}
