package com.mahdi.website.exeception.translatorExceptions;

import com.mahdi.website.exeception.BusinessException;

public class DuplicateTranslatorNameException extends BusinessException {

    public DuplicateTranslatorNameException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorNameException(String errorMessage) {
        super(errorMessage);
    }
}
