package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.model.Address;

import java.util.List;

public interface AddressServiceInterface {

    Address findAddressById(Long id);

    List<AddressDTO> updateAddress(List<AddressDTO> addressDTOList);
}
