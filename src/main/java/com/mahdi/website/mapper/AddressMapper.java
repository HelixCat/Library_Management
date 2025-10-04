package com.mahdi.website.mapper;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDTO> {
}
