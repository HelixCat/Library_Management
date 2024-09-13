package com.mahdi.website.exception.user;

import com.mahdi.website.exception.BusinessException;

public class DuplicateNationalCodeException extends BusinessException {

    public DuplicateNationalCodeException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateNationalCodeException(String errorMessage) {
        super(errorMessage);
    }
}
