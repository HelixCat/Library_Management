package com.mahdi.website.exeception;

public class DupplicateUserNameException extends BusinessException {

    public DupplicateUserNameException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DupplicateUserNameException(String errorMessage) {
        super(errorMessage);
    }
}
