package com.mahdi.website.exception.translator;

import com.mahdi.website.exception.BusinessException;

public class DuplicateTranslatorNationalCodeException extends BusinessException {

    public DuplicateTranslatorNationalCodeException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorNationalCodeException(String errorMessage) {
        super(errorMessage);
    }
}
