package com.mahdi.website.exception.translator;

import com.mahdi.website.exception.BusinessException;

public class DuplicateTranslatorPhoneNumberException extends BusinessException {

    public DuplicateTranslatorPhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
