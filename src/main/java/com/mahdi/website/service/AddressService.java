package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.exeception.AddressNotByIdFoundException;
import com.mahdi.website.model.Address;
import com.mahdi.website.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements AddressServiceInterface {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotByIdFoundException("Address with id " + id + " does not exist"));
    }

    @Override
    public void updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressDTO.getId()).orElseThrow(() -> new AddressNotByIdFoundException("Address with id " + addressDTO.getId() + " does not exist"));
        address.setCountry(addressDTO.getCountry());
        address.setProvince(addressDTO.getProvince());
        address.setCity(addressDTO.getCity());
        address.setPostalCode(addressDTO.getPostalCode());
        addressRepository.save(address);
    }




}
