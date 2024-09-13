package com.mahdi.website.exception.author;

import com.mahdi.website.exception.BusinessException;

public class DuplicateAuthorEmailException extends BusinessException {

    public DuplicateAuthorEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateAuthorEmailException(String errorMessage) {
        super(errorMessage);
    }
}
