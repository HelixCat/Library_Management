package com.mahdi.website.exeception.translatorExceptions;

import com.mahdi.website.exeception.BusinessException;

public class TranslatorNotFoundException extends BusinessException {

    public TranslatorNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public TranslatorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
