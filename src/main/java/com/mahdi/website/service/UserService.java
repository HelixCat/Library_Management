package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.AddressRepository;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.service.exeception.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AddressRepository addressRepository, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = prepareUser(null, userDTO);
        userRepository.save(user);
    }

    private User prepareUser(User userDetail, UserDTO userDTO) {
        User user = Objects.nonNull(userDetail) ? userDetail : new User();
        user.setActive(Boolean.TRUE);
        user.setManager(prepareAdminUser(userDTO));
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setNationalCode(userDTO.getNationalCode());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(prepareHashedPassword(userDTO.getPassword()));
        user.setAddresses(prepareAddress(userDTO.getAddressDTO(), user));
        user.setProfileImage(userDTO.getImage());
        user.setJob(userDTO.getJob());
        return user;
    }

    private List<Address> prepareAddress(AddressDTO addressDTO, User user) {
        List<Address> addressList = new ArrayList<>();
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setActive(Boolean.TRUE);
        address.setUser(user);
        addressList.add(address);
        return addressList;
    }

    private Boolean prepareAdminUser(UserDTO userDTO) {
        if (Objects.equals(userDTO.getNationalCode(), "3240005905")) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
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
        return userRepository.findByEmail(email);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDTO loadUserDTOByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return prepareUserDTO(user);
    }

    @Override
    public UserDTO loadUserDTOByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return prepareUserDTO(user);
    }

    @Override
    public void updateUser(String userName, UserDTO userDTO) throws Exception {
        User user = loadUserByUserName(userName);
        if (Objects.nonNull(user)) {
            user = prepareUser(user, userDTO);
            userRepository.save(user);
        } else {
            throw new Exception("user not found with userName " + userName);
        }
    }

    @Override
    public void updateUserPassword(String username, ChangePasswordDTO changePasswordDTO) {
        changePasswordDTO.setUserName(username);
        changePassword(changePasswordDTO);
    }

    private void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = loadUserByUserName(changePasswordDTO.getUserName());
        if (Objects.nonNull(user)) {
            if (Boolean.TRUE.equals(isValidPassword(changePasswordDTO.getOldPassword(), user.getPassword()))) {
                String hashedPassword = prepareHashedPassword(changePasswordDTO.getNewPassword());
                userRepository.updateUserPassword(user.getUsername(), hashedPassword);
            } else {
                throw new BusinessException("گذرواژه را اشتباه وارد کرده اید");
            }
        } else {
            throw new BusinessException(" کاربری با این نام کاربری وجود ندارد " + changePasswordDTO.getUserName());
        }
    }

    private UserDTO prepareUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setManager(user.getManager());
        userDTO.setNationalCode(user.getNationalCode());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setImage(user.getProfileImage());
        userDTO.setJob(user.getJob());
        userDTO.setBase64ProfileImage(prepareByteArrayToBase64(user.getProfileImage()));
        userDTO.setAddressDTO(prepareAddressDTO(user.getAddresses()));
        return userDTO;
    }

    private String prepareByteArrayToBase64(byte[] profileImage) {
        return Base64.getEncoder().encodeToString(profileImage);
    }

    private AddressDTO prepareAddressDTO(List<Address> addressList) {
        Address address = addressList.stream().findFirst().orElse(null);
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public Boolean isValidPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
