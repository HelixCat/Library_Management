package com.mahdi.website.exception.publisher;

import com.mahdi.website.exception.BusinessException;

public class PublisherNotFoundException extends BusinessException {

    public PublisherNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public PublisherNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
