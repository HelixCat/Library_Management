package com.mahdi.website.exeception;

public class DuplicatePublisherPhoneNumberException extends BusinessException{

    public DuplicatePublisherPhoneNumberException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public DuplicatePublisherPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
