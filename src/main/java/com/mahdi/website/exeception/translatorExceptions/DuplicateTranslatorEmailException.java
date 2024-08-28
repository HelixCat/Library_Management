package com.mahdi.website.exeception.translatorExceptions;

import com.mahdi.website.exeception.BusinessException;

public class DuplicateTranslatorEmailException extends BusinessException {

    public DuplicateTranslatorEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorEmailException(String errorMessage) {
        super(errorMessage);
    }
}
