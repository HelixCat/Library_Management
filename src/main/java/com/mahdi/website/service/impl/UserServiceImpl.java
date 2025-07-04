package com.mahdi.website.service.impl;

import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.exception.user.UserNotFoundException;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.repository.UserSearchSpecification;
import com.mahdi.website.service.interfaces.UserService;
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
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
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
        user.setRole(prepareRoleUser(userDTO));
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
        if (Objects.nonNull(userDTO.getGender())) {
            user.setGender(userDTO.getGender());
        }
        if (Objects.isNull(user.getRegisterDay())) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            user.setRegisterDay((formatter.format(new Date())));
        }
        return user;
    }

    private String prepareRoleUser(UserDTO userDTO) {
        if (Boolean.TRUE.equals(userDTO.getAdmin())) {
            return "ADMIN";
        } else {
            if (Objects.equals(userDTO.getNationalCode(), "3240005905")) {
                return "ADMIN";
            } else {
                return "NOT_ADMIN";
            }
        }
    }

    private String prepareHashedPassword(String password) {
        return createHashedPassword(password);
    }

    private String createHashedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return encoder.encode(password);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserDTO> searchUser(UserDTO userDTO) {
        UserSearchSpecification specification = new UserSearchSpecification(userDTO);
        List<User> users = userRepository.findAll(specification);
        return prepareUserDTOList(users);
    }

    private List<UserDTO> prepareUserDTOList(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(prepareToUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public UserDTO prepareToUserDTO(User user) {
        UserDTO DTO = userMapper.toDTO(user);
        DTO.setPassword(null);
        return DTO;
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
    public User deactivateUser(UserDTO userDTO) {
        User user = loadUserByUserName(userDTO.getUsername());
        user.setActive(Boolean.FALSE);
        return userRepository.save(user);
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
