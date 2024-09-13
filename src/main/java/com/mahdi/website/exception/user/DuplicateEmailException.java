package com.mahdi.website.exception.user;

import com.mahdi.website.exception.BusinessException;

public class DuplicateEmailException extends BusinessException {

    public DuplicateEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateEmailException(String errorMessage) {
        super(errorMessage);
    }
}
