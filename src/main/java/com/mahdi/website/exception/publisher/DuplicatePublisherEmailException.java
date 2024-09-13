package com.mahdi.website.exception.publisher;

import com.mahdi.website.exception.BusinessException;

public class DuplicatePublisherEmailException extends BusinessException {

    public DuplicatePublisherEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePublisherEmailException(String errorMessage) {
        super(errorMessage);
    }
}
