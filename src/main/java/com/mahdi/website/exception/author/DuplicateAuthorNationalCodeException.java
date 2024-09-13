package com.mahdi.website.exception.author;

import com.mahdi.website.exception.BusinessException;

public class DuplicateAuthorNationalCodeException extends BusinessException {

    public DuplicateAuthorNationalCodeException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateAuthorNationalCodeException(String errorMessage) {
        super(errorMessage);
    }
}
