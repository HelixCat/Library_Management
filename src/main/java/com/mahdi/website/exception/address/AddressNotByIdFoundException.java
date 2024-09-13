package com.mahdi.website.exception.address;

import com.mahdi.website.exception.BusinessException;

public class AddressNotByIdFoundException  extends BusinessException {

    public AddressNotByIdFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }

    public AddressNotByIdFoundException(String errorMessage) {
        super(errorMessage);
    }
}
