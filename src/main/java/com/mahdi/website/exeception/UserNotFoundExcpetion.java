package com.mahdi.website.service.exeception;

public class UserNotFoundExcpetion extends BusinessException{

    public UserNotFoundExcpetion(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public UserNotFoundExcpetion(String errorMessage) {
        super(errorMessage);
    }
}
