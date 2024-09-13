package com.mahdi.website.exception.author;

import com.mahdi.website.exception.BusinessException;

public class DuplicateAuthorPhoneNumberException extends BusinessException {

    public DuplicateAuthorPhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateAuthorPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
