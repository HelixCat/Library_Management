package com.mahdi.website.exeception;

public class AddressNotByIdFoundException  extends BusinessException {

    public AddressNotByIdFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public AddressNotByIdFoundException(String errorMessage) {
        super(errorMessage);
    }
}
