package com.mahdi.website.exception.publisher;

import com.mahdi.website.exception.BusinessException;

public class DuplicatePublisherPhoneNumberException extends BusinessException {

    public DuplicatePublisherPhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePublisherPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
