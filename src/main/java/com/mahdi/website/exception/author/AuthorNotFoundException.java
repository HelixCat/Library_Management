package com.mahdi.website.exception.author;

import com.mahdi.website.exception.BusinessException;

public class AuthorNotFoundException extends BusinessException {

    public AuthorNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public AuthorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
