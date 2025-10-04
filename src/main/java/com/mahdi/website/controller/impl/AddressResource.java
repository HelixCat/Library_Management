package com.mahdi.website.controller.impl;

import com.mahdi.website.controller.rest.AddressRemote;
import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address-management")
public class AddressResource implements AddressRemote {

    private final AddressMapper addressMapper;
    private final AddressService addressService;

    @Override
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTO(addressService.saveAddress(addressDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTO(addressService.updateAddress(addressDTO)), HttpStatusCode.valueOf(204));
    }

    @Override
    public ResponseEntity<AddressDTO> deactivateAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTO(addressService.deactivateAddress(addressDTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AddressDTO>> searchAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTOList(addressService.search(addressDTO)), HttpStatus.FOUND);
    }
}
