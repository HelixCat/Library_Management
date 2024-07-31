package com.mahdi.website.exeception;

public class BusinessException extends RuntimeException{

    public BusinessException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}