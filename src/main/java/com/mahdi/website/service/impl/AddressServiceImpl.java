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
    @Cacheable(value = "addresses", key = "#id", unless = "#result == null")
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(AddressByIdNotFoundException::new);
    }

    @Override
    @Cacheable(value = "addressSearch", key = "T(java.util.Objects).hash(#addressDTO.city, #addressDTO.postalCode, #addressDTO.street)", unless = "#result == null or #result.isEmpty()")
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
            address = findAddressById(addressDTO.getId());
        } else {
            address = findAddressByPostalCode(addressDTO.getPostalCode());
        }
        return addressRepository.save(address);
    }

    @Override
    @Cacheable(value = "addresses", key = "#postalCode", unless = "#result == null")
    public Address findAddressByPostalCode(String postalCode) {
        return addressRepository.findByPostalCode(postalCode).orElseThrow(AddressByPostalCodeNotFoundException::new);
    }

    @Override
    @Caching(put = {
        @CachePut(value = "addresses", key = "#result.id")
    }, evict = {
        @CacheEvict(value = "addressSearch", allEntries = true)
    })
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
