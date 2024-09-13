package com.mahdi.website.exception.user;

import com.mahdi.website.exception.BusinessException;

public class UserNotFoundExcpetion extends BusinessException {

    public UserNotFoundExcpetion(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public UserNotFoundExcpetion(String errorMessage) {
        super(errorMessage);
    }
}
