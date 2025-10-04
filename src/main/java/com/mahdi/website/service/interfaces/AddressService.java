package com.mahdi.website.service.interfaces;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> search(AddressDTO addressDTO);

    Address saveAddress(AddressDTO addressDTO);

    Address deactivateAddress(AddressDTO addressDTO);

    Address findAddressByPostalCode(AddressDTO addressDTO);

    Address findAddressById(AddressDTO addressDTO);

    Address updateAddress(AddressDTO addressDTOList);
}
