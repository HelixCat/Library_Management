package com.mahdi.website.exeception;

public class PublisherNotFoundException extends BusinessException {

    public PublisherNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public PublisherNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
