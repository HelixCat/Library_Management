package com.mahdi.website.exeception;

public class DuplicatePublisherEmailException extends BusinessException{

    public DuplicatePublisherEmailException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePublisherEmailException(String errorMessage) {
        super(errorMessage);
    }
}
