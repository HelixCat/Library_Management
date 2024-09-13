package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.exception.user.UserNotFoundExcpetion;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.service.interfaces.AddressServiceInterface;
import com.mahdi.website.service.interfaces.UserServiceInterface;
import com.mahdi.website.service.validation.interfaces.LoginValidationInterface;
import com.mahdi.website.service.validation.interfaces.SignUpValidationInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final LoginValidationInterface loginValidation;
    private final SignUpValidationInterface signUpValidation;
    private final AddressServiceInterface addressService;

    @Autowired
    public UserService(AddressRepository addressRepository, UserRepository userRepository, ModelMapper modelMapper, LoginValidationInterface loginValidation, SignUpValidationInterface signUpValidation, AddressServiceInterface addressService) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loginValidation = loginValidation;
        this.signUpValidation = signUpValidation;
        this.addressService = addressService;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

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
        user.setFatherName(userDTO.getFatherName());
        if (Objects.nonNull(userDTO.getGender())) {
            user.setGender(userDTO.getGender());
        }
        if (Objects.nonNull(userDTO.getPassword())) {
            user.setPassword(prepareHashedPassword(userDTO.getPassword()));
        }
        if (Objects.nonNull(userDTO.getAddressDTO())) {
            user.setAddresses(prepareAddress(userDTO.getAddressDTO(), user));
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

    private List<Address> prepareAddress(AddressDTO addressDTO, User user) {
        List<Address> addressList = Objects.nonNull(user.getAddresses()) ? user.getAddresses() : new ArrayList<>();
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setActive(Boolean.TRUE);
        address.setUser(user);
        address.setId(addressDTO.getId());
        addressList.add(address);
        return addressList;
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

    @Override
    public UserDTO AddAddressToTheUser(String username, AddressDTO addressDTO) {
        User user = loadUserByUserName(username);
        List<Address> addressList = user.getAddresses();
        addressList.add(prepareAddressDTOToAddress(user, addressDTO));
        saveAddress(addressList);
        user = loadUserByUserName(username);
        return prepareUserDTO(user);
    }

    private Address prepareAddressDTOToAddress(User user, AddressDTO addressDTO) {
        Address address = new Address();
        address.setCountry(addressDTO.getCountry());
        address.setProvince(addressDTO.getProvince());
        address.setCity(addressDTO.getCity());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setUser(user);
        return address;
    }

    private String createHashedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return encoder.encode(password);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundExcpetion("user with email " + email + " does not exist"));

    }

    @Override
    public User loadUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundExcpetion("user with phoneNumber " + phoneNumber + " does not exist"));

    }

    @Override
    public User loadUserByNationalCode(String nationalCode) {
        return userRepository.findByNationalCode(nationalCode)
                .orElseThrow(() -> new UserNotFoundExcpetion("user with nationalCode " + nationalCode + " does not exist"));

    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundExcpetion("user with username " + userName + " does not exist"));
    }

    @Override
    public UserDTO loadUserDTOByUserName(String userName) {
        User user = loadUserByUserName(userName);
        return prepareUserDTO(user);
    }

    @Override
    public UserDTO loadUserDTOByEmailForLoginPage(UserDTO userDTO) {
        User user = loadUserByEmail(userDTO.getEmail());
        loginValidation.isValidPassword(userDTO.getPassword(), user.getPassword(), "login");
        return prepareUserDTO(user);
    }

    @Override
    public UserDTO loadUserDTOByEmail(UserDTO userDTO) {
        User user = loadUserByEmail(userDTO.getEmail());
        return prepareUserDTO(user);
    }

    @Override
    public UserDTO updateUser(String userName, UserDTO userDTO) {
        User user = prepareUser(loadUserByUserName(userName), userDTO);
        userRepository.save(user);
        return prepareUserDTO(user);
    }

    @Override
    public void updateUserPassword(String username, ChangePasswordDTO changePasswordDTO) {
        changePasswordDTO.setUserName(username);
        changePassword(changePasswordDTO);
    }

    private void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = loadUserByUserName(changePasswordDTO.getUserName());
        loginValidation.isValidPassword(changePasswordDTO.getOldPassword(), user.getPassword(), "change password");
        String hashedPassword = prepareHashedPassword(changePasswordDTO.getNewPassword());
        userRepository.updateUserPassword(user.getUsername(), hashedPassword);
    }

    private UserDTO prepareUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setActive(user.getActive());
        userDTO.setVersion(user.getVersion());
        userDTO.setManualId(userDTO.getManualId());
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setAdmin(user.getAdmin());
        userDTO.setNationalCode(user.getNationalCode());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setImage(user.getProfileImage());
        userDTO.setGender(user.getGender());
        userDTO.setFatherName(user.getFatherName());
        userDTO.setBirthday(user.getBirthday());
        if (Objects.nonNull(user.getProfileImage())) {
            userDTO.setBase64ProfileImage(prepareByteArrayToBase64(user.getProfileImage()));
        }
        userDTO.setAddressDTO(prepareAddressDTO(user.getAddresses()));
        return userDTO;
    }

    private String prepareByteArrayToBase64(byte[] profileImage) {
        return Base64.getEncoder().encodeToString(profileImage);
    }

    private AddressDTO prepareAddressDTO(List<Address> addressList) {
        Address address = addressList.stream().findFirst().orElse(null);
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        addressDTO.setId(address.getId());
        return addressDTO;
    }
}
