package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.model.Address;

public interface AddressServiceInterface {
    Address findAddressById(Long id);

    void updatePublisherAddress(AddressDTO addressDTO);
}
