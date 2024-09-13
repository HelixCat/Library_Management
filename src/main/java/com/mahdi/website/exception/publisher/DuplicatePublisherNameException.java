package com.mahdi.website.exception.publisher;

import com.mahdi.website.exception.BusinessException;

public class DuplicatePublisherNameException extends BusinessException {

    public DuplicatePublisherNameException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePublisherNameException(String errorMessage) {
        super(errorMessage);
    }
}
