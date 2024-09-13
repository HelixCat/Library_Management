package com.mahdi.website.exception.book;

import com.mahdi.website.exception.BusinessException;

public class DuplicateBookIdException extends BusinessException {

    public DuplicateBookIdException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateBookIdException(String errorMessage) {
        super(errorMessage);
    }
}
