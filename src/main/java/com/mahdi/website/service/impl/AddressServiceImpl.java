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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public Address findAddressById(AddressDTO addressDTO) {
        return addressRepository.findById(addressDTO.getId()).orElseThrow(AddressByIdNotFoundException::new);
    }

    @Override
    @Cacheable(value = "addresses", key = "#addressDTO.id", unless = "#result == null")
    public AddressDTO findAddressDTOById(AddressDTO addressDTO) {
        return addressMapper.toDTO(findAddressById(addressDTO));
    }

    @Override
    @Cacheable(value = "addressSearch", key = "T(java.util.Objects).hash(#addressDTO.city, #addressDTO.postalCode)", unless = "#result == null or #result.isEmpty()")
    public List<Address> search(AddressDTO addressDTO) {
        AddressSearchSpecification specification = new AddressSearchSpecification(addressDTO);
        return addressRepository.findAll(specification);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "addresses", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "addressSearch", allEntries = true)
    })
    public Address saveAddress(AddressDTO addressDTO) {
        return addressRepository.save(addressMapper.toEntity(addressDTO));
    }

    @Override
    @Caching(put = {
            @CachePut(value = "addresses", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "addressSearch", allEntries = true)
    })
    public Address deactivateAddress(AddressDTO addressDTO) {
        Address address;
        if (Objects.nonNull(addressDTO.getId())) {
            address = findAddressById(addressDTO);
        } else {
            address = findAddressByPostalCode(addressDTO);
        }
        return addressRepository.save(address);
    }

    @Override
    public Address findAddressByPostalCode(AddressDTO addressDTO) {
        return addressRepository.findAddressByPostalCode(addressDTO.getPostalCode()).orElseThrow(AddressByPostalCodeNotFoundException::new);
    }

    @Override
    @Cacheable(value = "addresses", key = "#addressDTO.postalCode", unless = "#result == null")
    public AddressDTO findAddressDTOByPostalCode(AddressDTO addressDTO) {
        return addressMapper.toDTO(findAddressByPostalCode(addressDTO));
    }

    @Override
    @Caching(put = {
            @CachePut(value = "addresses", key = "#result.id")
    }, evict = {
            @CacheEvict(value = "addressSearch", allEntries = true)
    })
    public Address updateAddress(AddressDTO addressDTO) {

        Address address = findAddressById(addressDTO);
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
