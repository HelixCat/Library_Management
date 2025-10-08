package com.mahdi.website.service.impl;

import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
import com.mahdi.website.enumeration.Role;
import com.mahdi.website.exception.user.UserNotFoundException;
import com.mahdi.website.mapper.UserMapper;
import com.mahdi.website.model.User;
import com.mahdi.website.repository.UserRepository;
import com.mahdi.website.repository.UserSearchSpecification;
import com.mahdi.website.service.interfaces.UserService;
import com.mahdi.website.service.validation.impl.PasswordValidationService;
import com.mahdi.website.service.validation.interfaces.SignUpValidationInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordValidationService passwordValidationService;
    private final SignUpValidationInterface signUpValidation;

    @Override
    @Caching(put = {
            @CachePut(value = "users", key = "#result.username"),
            @CachePut(value = "userDetails", key = "#result.email")
    })
    @Transactional
    public User saveUser(UserDTO userDTO) {
        log.info("Saving user: {}", userDTO.getUsername());
        signUpValidation.signUpValidation(userDTO);
        User user = prepareUser(null, userDTO);
        User savedUser = userRepository.save(user);
        log.info("User saved and cached: {}", savedUser.getUsername());
        return savedUser;
    }

    private User prepareUser(User userDetail, UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("userDTO must not be null");
        }

        User user = Objects.nonNull(userDetail) ? userDetail : new User();
        user.setActive(Boolean.TRUE);

        Optional.ofNullable(userDTO.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(userDTO.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(userDTO.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(userDTO.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userDTO.getNationalCode()).ifPresent(user::setNationalCode);
        Optional.ofNullable(userDTO.getPhoneNumber()).ifPresent(user::setPhoneNumber);
        Optional.ofNullable(userDTO.getImage()).ifPresent(user::setProfileImage);
        Optional.ofNullable(userDTO.getBirthday()).ifPresent(user::setBirthday);
        Optional.ofNullable(userDTO.getGender()).ifPresent(user::setGender);

        Optional.ofNullable(userDTO.getPassword())
                .map(this::prepareHashedPassword)
                .ifPresent(user::setPassword);
        if (Objects.isNull(user.getRegisterDay())) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            user.setRegisterDay(formatter.format(new Date()));
        }

        if ((Objects.isNull(user.getRoles())) ||(user.getRoles().isEmpty())) {
            if (Objects.nonNull(userDTO.getRoles()) && !userDTO.getRoles().isEmpty()) {
                user.getRoles().addAll(userDTO.getRoles());
            } else {
                user.setRoles(new HashSet<>(Collections.singletonList(prepareRoleUser(userDTO))));
            }
        }

        return user;
    }


    private Role prepareRoleUser(UserDTO userDTO) {
        if (Objects.equals(userDTO.getNationalCode(), "3240005905")) {
            return Role.ROLE_ADMIN;
        } else {
            return Role.ROLE_USER;
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
    @Cacheable(value = "users", key = "#userName", unless = "#result == null")
    public User loadUserByUserName(UserDTO userDTO) {
        log.info("Loading user by username from database: {}", userDTO.getUsername());
        return userRepository.findByUserName(userDTO.getUsername())
                .orElseThrow(() -> {
                    log.warn("User not found: {}", userDTO.getUsername());
                    return new UserNotFoundException();
                });
    }

    @Override
    @Cacheable(value = "userDetails", key = "#email", unless = "#result == null")
    public User loadUserByEmail(UserDTO userDTO) {
        log.info("Loading user by email from database: {}", userDTO.getEmail());
        return userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> {
                    log.warn("User not found by email: {}", userDTO.getEmail());
                    return new UserNotFoundException();
                });
    }

    @Cacheable(
            value = "userSearch",
            key = "T(java.util.Objects).hash(#userDTO.username, #userDTO.email, #userDTO.firstName, #userDTO.lastName, #userDTO.active)",
            unless = "#result == null or #result.isEmpty()"
    )
    public List<User> searchUser(UserDTO userDTO) {
        log.info("Searching users with criteria: {}", userDTO);
        UserSearchSpecification specification = new UserSearchSpecification(userDTO);
        return userRepository.findAll(specification);
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "users", key = "#result.username"),
                    @CachePut(value = "userDetails", key = "#result.email")
            },
            evict = {
                    @CacheEvict(value = "userSearch", allEntries = true)
            }
    )
    public User updateUser(UserDTO userDTO) {
        log.info("Updating user: {}", userDTO.getUsername());
        User user = prepareUser(loadUserById(userDTO), userDTO);
        User updatedUser = userRepository.save(user);
        log.info("User updated and cache refreshed: {}", updatedUser.getUsername());
        return updatedUser;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "users", key = "#result.username"),
                    @CachePut(value = "userDetails", key = "#result.email")
            },
            evict = {
                    @CacheEvict(value = "userSearch", allEntries = true)
            }
    )
    public User deactivateUser(UserDTO userDTO) {
        log.info("Deactivating user: {}", userDTO.getUsername());
        User user = loadUserByUserName(userDTO);
        user.setActive(Boolean.FALSE);
        User deactivatedUser = userRepository.save(user);
        log.info("User deactivated and cache updated: {}", deactivatedUser.getUsername());
        return deactivatedUser;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "users", key = "#result.username"),
                    @CachePut(value = "userDetails", key = "#result.email")
            }
    )
    public User updateUserPassword(ChangePasswordDTO changePasswordDTO) {
        log.info("Updating password for user: {}", changePasswordDTO.getUserName());
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(changePasswordDTO.getUserName());
        User user = loadUserByUserName(userDTO);
        passwordValidationService.isValidPassword(changePasswordDTO.getOldPassword(), user.getPassword(), "change password");
        String hashedPassword = prepareHashedPassword(changePasswordDTO.getNewPassword());
        user.setPassword(hashedPassword);
        User updatedUser = userRepository.save(user);
        log.info("Password updated and cache refreshed for user: {}", updatedUser.getUsername());
        return updatedUser;
    }

    private String prepareByteArrayToBase64(byte[] profileImage) {
        return Base64.getEncoder().encodeToString(profileImage);
    }

    @Override
    public User authenticateUser(UserDTO userDTO) {
        log.info("Authenticating user: {}", userDTO.getUsername());
        User user = loadUserByUserName(userDTO);
        passwordValidationService.isValidPassword(userDTO.getPassword(), user.getPassword(), "login");
        log.info("User authenticated successfully: {}", userDTO.getUsername());
        return user;
    }

    @Override
    public User loadUserById(UserDTO userDTO) {
        return userRepository.findById(userDTO.getId()).orElseThrow(() -> {
            log.warn("User not found by id: {}", userDTO.getId());
            return new UserNotFoundException();
        });
    }
}