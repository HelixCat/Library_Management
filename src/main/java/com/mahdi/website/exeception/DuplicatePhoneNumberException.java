package com.mahdi.website.exeception;

public class DuplicatePhoneNumberException extends BusinessException {

    public DuplicatePhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
