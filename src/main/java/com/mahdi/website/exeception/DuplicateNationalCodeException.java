package com.mahdi.website.exeception;

public class DuplicateNationalCodeException extends BusinessException {

    public DuplicateNationalCodeException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateNationalCodeException(String errorMessage) {
        super(errorMessage);
    }
}
