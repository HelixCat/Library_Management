package com.mahdi.website.exeception;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateEmailException(String errorMessage) {
        super(errorMessage);
    }
}
