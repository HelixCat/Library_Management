package com.mahdi.website.exeception;

public class IncorrectPasswordExceprion extends BusinessException{
    public IncorrectPasswordExceprion(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public IncorrectPasswordExceprion(String errorMessage) {
        super(errorMessage);
    }
}
