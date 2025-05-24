package com.mahdi.website.concerns;

import com.mahdi.website.model.Logg;
import com.mahdi.website.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LogHandler {

    private final LogRepository logRepository;

    public StringBuilder prepareLog(JoinPoint joinPoint, String packageName, String result, Exception ex) {
        String logType = "Before";
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("log type for package ");
        stringBuilder.append(packageName).append(" is ");
        stringBuilder.append(logType).append("\n ");
        stringBuilder.append("class name : ");
        stringBuilder.append(className).append("\n ");
        stringBuilder.append("methodName : ");
        stringBuilder.append(methodName).append("\n ");
        stringBuilder.append("Arguments passed to the method {\n ");
        stringBuilder.append(Arrays.toString(joinPoint.getArgs())).append("\n ");
        stringBuilder.append(" } \n ");
        if (StringUtils.hasText(result)) {
            stringBuilder.append("Result of the method is {\n ");
            stringBuilder.append(result).append("\n ");
            stringBuilder.append("} ");
        }
        if (Objects.nonNull(ex)) {
            stringBuilder.append("exception of the method is {\n ");
            stringBuilder.append(ex.getCause()).append("\n ");
            stringBuilder.append(Arrays.toString(ex.getStackTrace()));
            stringBuilder.append("} ");
        }
        return stringBuilder;
    }

    public void mangoDBLoggSaver(Date date, String level, String message) {
        Logg logg = new Logg();
        logg.setDate(date);
        logg.setLevel(level);
        logg.setMessage(message);
        logRepository.save(logg);
    }
}
