package com.mahdi.website.service.impl;

import com.mahdi.website.exception.address.AddressByIdNotFoundException;
import com.mahdi.website.model.Address;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.mapper.AddressMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressMapper addressMapper;
    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAddressById_found() {
        Address address = new Address();
        address.setId(1L);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        Address result = addressServiceImpl.findAddressById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void findAddressById_notFound() {
        when(addressRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(AddressByIdNotFoundException.class, () -> addressServiceImpl.findAddressById(2L));
        verify(addressRepository, times(1)).findById(2L);
    }
}
