package com.mahdi.website.exception;

public class IncorrectPasswordExceprion extends BusinessException{

    private String context;

    public IncorrectPasswordExceprion(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public IncorrectPasswordExceprion(String errorMessage, String context) {
        super(errorMessage);
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
