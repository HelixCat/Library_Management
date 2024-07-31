package com.example.website;

import com.mahdi.website.dto.UserDTO;
import static org.mockito.Mockito.*;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.service.UserServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceInterface userService;

    @Test
    public void testSaveUser() throws Exception {
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

        User user = userService.saveUser(userDTO);

        assertNotNull(user);
        assertEquals("Masood", user.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}
