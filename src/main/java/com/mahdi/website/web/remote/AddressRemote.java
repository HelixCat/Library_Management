package com.mahdi.website.web.remote;

import com.mahdi.website.dto.AddressDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "Address management APIs")
public interface AddressRemote {
    @Operation(summary = "Search addresses", description = "Search for addresses based on provided criteria")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Addresses found", content = @Content(schema = @Schema(implementation = AddressDTO.class))),
        @ApiResponse(responseCode = "404", description = "No addresses found")
    })
    @GetMapping("/search")
    ResponseEntity<List<AddressDTO>> searchAddresses(@RequestBody AddressDTO addressDTO);

    @Operation(summary = "Save new address", description = "Add a new address to the system")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Address created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/save")
    ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO);

    @Operation(summary = "Update address", description = "Update an existing address")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address updated successfully"),
        @ApiResponse(responseCode = "404", description = "Address not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/update")
    ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO);

    @Operation(summary = "Find address by ID", description = "Retrieve an address by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Address found"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<AddressDTO> findAddressById(@RequestBody AddressDTO addressDTO);
}
