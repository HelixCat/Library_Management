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
    public void updatePublisherAddress(AddressDTO addressDTO) {
        addressRepository.save(modelMapper.map(addressDTO, Address.class));
    }

}
