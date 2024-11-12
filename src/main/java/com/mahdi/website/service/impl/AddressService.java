package com.mahdi.website.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import com.mahdi.website.model.Address;
import com.mahdi.website.dto.AddressDTO;
import org.springframework.stereotype.Service;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.service.interfaces.AddressServiceInterface;
import com.mahdi.website.exception.address.AddressNotByIdFoundException;


@Service
@RequiredArgsConstructor
public class AddressService implements AddressServiceInterface {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(AddressNotByIdFoundException::new);
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) {
        Address address = findAddressById(addressDTO.getId());
        if (Objects.nonNull(addressDTO.getCountry())) {
            address.setCountry(addressDTO.getCountry());
        }
        if (Objects.nonNull(addressDTO.getProvince())) {
            address.setProvince(addressDTO.getProvince());
        }
        if (Objects.nonNull(addressDTO.getCity())) {
            address.setCity(addressDTO.getCity());
        }
        if (Objects.nonNull(addressDTO.getPostalCode())) {
            address.setPostalCode(addressDTO.getPostalCode());
        }
        return addressMapper.toDTO(addressRepository.save(address));
    }
}
