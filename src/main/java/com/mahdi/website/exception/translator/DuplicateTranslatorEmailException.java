package com.mahdi.website.exception.translator;

import com.mahdi.website.exception.BusinessException;

public class DuplicateTranslatorEmailException extends BusinessException {

    public DuplicateTranslatorEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicateTranslatorEmailException(String errorMessage) {
        super(errorMessage);
    }
}
