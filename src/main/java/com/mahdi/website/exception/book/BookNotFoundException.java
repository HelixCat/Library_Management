package com.mahdi.website.exception.book;

import com.mahdi.website.exception.BusinessException;

public class BookNotFoundException extends BusinessException {

    public BookNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
