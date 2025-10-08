package com.mahdi.website.web.impl;

import com.mahdi.website.web.remote.AddressRemote;
import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.service.interfaces.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/address-management")
@Tag(name = "Address", description = "Address management operations")
public class AddressResource implements AddressRemote {

    private final AddressMapper addressMapper;
    private final AddressService addressService;

    @Override
    @Operation(summary = "Search addresses", description = "Search for addresses based on provided criteria")
    public ResponseEntity<List<AddressDTO>> searchAddresses(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTOList(addressService.search(addressDTO)), HttpStatus.FOUND);
    }

    @Override
    @Operation(summary = "Save new address", description = "Add a new address to the system")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTO(addressService.saveAddress(addressDTO)), HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Update address", description = "Update an existing address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTO(addressService.updateAddress(addressDTO)), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Find address by ID", description = "Retrieve an address by its ID")
    public ResponseEntity<AddressDTO> findAddressById(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressMapper.toDTO(addressService.findAddressById(addressDTO)), HttpStatus.OK);
    }
}
