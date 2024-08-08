package com.mahdi.website.exeception;

public class DuplicatePublisherNameException extends BusinessException{

    public DuplicatePublisherNameException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePublisherNameException(String errorMessage) {
        super(errorMessage);
    }
}
