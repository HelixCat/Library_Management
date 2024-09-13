package com.mahdi.website.exception.translator;

import com.mahdi.website.exception.BusinessException;

public class TranslatorNotFoundException extends BusinessException {

    public TranslatorNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public TranslatorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
