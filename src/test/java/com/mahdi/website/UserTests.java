package com.mahdi.website;

import com.mahdi.website.dto.UserDTO;
import static org.mockito.Mockito.*;

import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.service.impl.UserService;
import com.mahdi.website.service.validation.impl.SignUpValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SignUpValidation signUpValidation;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
//        userService = new UserService(null, userRepository, modelMapper, null, signUpValidation);
    }


    @Test
    public void testSaveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Masood");
        userDTO.setPassword("Ngc1369");
        userDTO.setFirstName("Masood");
        userDTO.setLastName("Ghomsheh");
        userDTO.setEmail("Masood@gmail.com");
        userDTO.setPhoneNumber("09134322415");
        userDTO.setGender("male");
        userDTO.setFatherName("Azziz");
        userDTO.setAdmin(Boolean.FALSE);
        userDTO.setNationalCode("3240005906");

        User user = new User();
        Address address = new Address();
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        user.setAddresses(addressList);

        doReturn(user).when(modelMapper).map(userDTO, User.class);
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(userDTO);

        // Then
        assertNotNull(savedUser);
        assertEquals("Ghomsheh", savedUser.getLastName());
        assertEquals("masood", savedUser.getFirstName());
        verify(modelMapper, times(1)).map(userDTO, User.class);
        verify(userRepository, times(1)).save(user);
    }
}
