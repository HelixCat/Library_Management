package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.exception.address.AddressByIdNotFoundException;
import com.mahdi.website.exception.address.AddressByPostalCodeNotFoundException;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.model.Address;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.repository.AddressSearchSpecification;
import com.mahdi.website.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(AddressByIdNotFoundException::new);
    }

    @Override
    public List<Address> search(AddressDTO addressDTO) {
        AddressSearchSpecification specification = new AddressSearchSpecification(addressDTO);
        return addressRepository.findAll(specification);
    }

    @Override
    public Address saveAddress(AddressDTO addressDTO) {
        return addressRepository.save(addressMapper.toEntity(addressDTO));
    }

    @Override
    public Address deactivateAddress(AddressDTO addressDTO) {
        Address address;
        if (Objects.nonNull(addressDTO.getId())) {
            address = findAddressById(addressDTO.getId());
        } else {
            address = findAddressByPostalCode(addressDTO.getPostalCode());
        }
        return addressRepository.save(address);
    }

    @Override
    public Address findAddressByPostalCode(String postalCode) {
        return addressRepository.findByPostalCode(postalCode).orElseThrow(AddressByPostalCodeNotFoundException::new);
    }

    @Override
    public Address updateAddress(AddressDTO addressDTO) {

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
        return addressRepository.save(address);
    }
}
