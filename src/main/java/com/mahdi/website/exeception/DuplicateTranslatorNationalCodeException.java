package com.mahdi.website.exeception;

public class DuplicateTranslatorNationalCodeException extends BusinessException {

    public DuplicateTranslatorNationalCodeException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorNationalCodeException(String errorMessage) {
        super(errorMessage);
    }
}
