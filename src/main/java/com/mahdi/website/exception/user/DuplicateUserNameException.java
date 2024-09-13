package com.mahdi.website.exception.user;

import com.mahdi.website.exception.BusinessException;

public class DuplicateUserNameException extends BusinessException {

    public DuplicateUserNameException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateUserNameException(String errorMessage) {
        super(errorMessage);
    }
}
