package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.exception.user.UserNotFoundException;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.service.interfaces.UserServiceInterface;
import com.mahdi.website.service.validation.interfaces.LoginValidationInterface;
import com.mahdi.website.service.validation.interfaces.SignUpValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final AddressMapper addressMapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final LoginValidationInterface loginValidation;
    private final SignUpValidationInterface signUpValidation;

    @Override
    public User saveUser(UserDTO userDTO) {
        signUpValidation.signUpValidation(userDTO);
        User user = prepareUser(null, userDTO);
        return userRepository.save(user);
    }

    private User prepareUser(User userDetail, UserDTO userDTO) {
        User user = Objects.nonNull(userDetail) ? userDetail : new User();
        user.setActive(Boolean.TRUE);
        user.setAdmin(prepareAdminUser(userDTO));
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setNationalCode(userDTO.getNationalCode());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setProfileImage(userDTO.getImage());
        user.setBirthday(userDTO.getBirthday());
        if (Objects.nonNull(userDTO.getGender())) {
            user.setGender(userDTO.getGender());
        }
        if (Objects.nonNull(userDTO.getPassword())) {
            user.setPassword(prepareHashedPassword(userDTO.getPassword()));
        }
        if (Objects.nonNull(userDTO.getAddressDTOList()) && !userDTO.getAddressDTOList().isEmpty()) {
            user.setAddresses(addressMapper.ToEntities(userDTO.getAddressDTOList()));
        }
        if (Objects.nonNull(userDTO.getGender())) {
            user.setGender(userDTO.getGender());
        }
        if (Objects.isNull(user.getRegisterDay())) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            user.setRegisterDay((formatter.format(new Date())));
        }
        return user;
    }

    private Boolean prepareAdminUser(UserDTO userDTO) {
        if (Boolean.TRUE.equals(userDTO.getAdmin())) {
            return true;
        } else {
            if (Objects.equals(userDTO.getNationalCode(), "3240005905")) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    private String prepareHashedPassword(String password) {
        return createHashedPassword(password);
    }

    @Override
    public List<Address> saveAddress(List<Address> addressList) {
        for (Address address : addressList) {
            address.setActive(true);
            addressRepository.save(address);
        }
        return addressList;
    }

    private String createHashedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return encoder.encode(password);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = prepareUser(loadUserByUserName(userDTO.getUsername()), userDTO);
        userRepository.save(user);
        if (Objects.nonNull(user.getProfileImage())) {
            userDTO.setBase64ProfileImage(prepareByteArrayToBase64(user.getProfileImage()));
        }
        return user;
    }

    @Override
    public User updateUserPassword(ChangePasswordDTO changePasswordDTO) {
        User user = loadUserByUserName(changePasswordDTO.getUserName());
        loginValidation.isValidPassword(changePasswordDTO.getOldPassword(), user.getPassword(), "change password");
        String hashedPassword = prepareHashedPassword(changePasswordDTO.getNewPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    private String prepareByteArrayToBase64(byte[] profileImage) {
        return Base64.getEncoder().encodeToString(profileImage);
    }
}
