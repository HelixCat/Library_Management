package com.mahdi.website.exeception.translatorExceptions;

import com.mahdi.website.exeception.BusinessException;

public class DuplicateTranslatorPhoneNumberException extends BusinessException {

    public DuplicateTranslatorPhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
