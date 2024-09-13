package com.mahdi.website.exception.user;

import com.mahdi.website.exception.BusinessException;

public class DuplicatePhoneNumberException extends BusinessException {

    public DuplicatePhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
