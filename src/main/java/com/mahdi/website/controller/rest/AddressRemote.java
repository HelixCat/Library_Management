package com.mahdi.website.controller.rest;

import com.mahdi.website.dto.AddressDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AddressRemote {
    @PostMapping("/save")
    ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO);

    @PutMapping("/update")
    ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO);

    @PutMapping("/deactivate")
    ResponseEntity<AddressDTO> deactivateAddress(@RequestBody AddressDTO addressDTO);

    @GetMapping("/search")
    ResponseEntity<List<AddressDTO>> searchAddress(@RequestBody AddressDTO addressDTO);
}
