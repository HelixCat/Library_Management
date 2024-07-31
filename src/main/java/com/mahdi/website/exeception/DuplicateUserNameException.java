package com.mahdi.website.exeception;

public class DuplicateUserNameException extends BusinessException {

    public DuplicateUserNameException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateUserNameException(String errorMessage) {
        super(errorMessage);
    }
}
