package com.mahdi.website.mapper;

import org.mapstruct.Mapper;
import com.mahdi.website.model.Address;
import com.mahdi.website.dto.AddressDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);
}
