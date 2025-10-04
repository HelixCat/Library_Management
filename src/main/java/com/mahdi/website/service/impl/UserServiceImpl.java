package com.mahdi.website.service.impl;

import com.mahdi.website.dto.ChangePasswordDTO;
import com.mahdi.website.dto.UserDTO;
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
    @Cacheable(value = "users", key = "#userName", unless = "#result == null")
    public User loadUserByUserName(String userName) {
        log.info("Loading user by username from database: {}", userName);
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    log.warn("User not found: {}", userName);
                    return new UserNotFoundException();
                });
    }

    @Override
    @Cacheable(value = "userDetails", key = "#email", unless = "#result == null")
    public User loadUserByEmail(String email) {
        log.info("Loading user by email from database: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User not found by email: {}", email);
                    return new UserNotFoundException();
                });
    }

    @Override
    @Cacheable(value = "userSearch",
            key = "T(java.util.Objects).hash(#userDTO.username, #userDTO.email, #userDTO.firstName, #userDTO.lastName, #userDTO.active)",
            unless = "#result == null or #result.isEmpty()")
    public List<UserDTO> searchUser(UserDTO userDTO) {
        log.info("Searching users with criteria: {}", userDTO);
        UserSearchSpecification specification = new UserSearchSpecification(userDTO);
        List<User> users = userRepository.findAll(specification);
        List<UserDTO> result = prepareUserDTOList(users);
        log.info("Found {} users matching criteria", result.size());
        return result;
    }

    private List<UserDTO> prepareUserDTOList(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(prepareToUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    @Cacheable(value = "userDetails", key = "#user.username", condition = "#user != null")
    public UserDTO prepareToUserDTO(User user) {
        UserDTO DTO = userMapper.toDTO(user);
        DTO.setPassword(null);
        return DTO;
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
        User user = prepareUser(loadUserByUserName(userDTO.getUsername()), userDTO);
        User updatedUser = userRepository.save(user);
        if (Objects.nonNull(user.getProfileImage())) {
            userDTO.setBase64ProfileImage(prepareByteArrayToBase64(user.getProfileImage()));
        }
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
        User user = loadUserByUserName(userDTO.getUsername());
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
        User user = loadUserByUserName(changePasswordDTO.getUserName());
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
        User user = loadUserByUserName(userDTO.getUsername());
        passwordValidationService.isValidPassword(userDTO.getPassword(), user.getPassword(), "login");
        log.info("User authenticated successfully: {}", userDTO.getUsername());
        return user;
    }

    @Override
    public User loadUserById(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> {
            log.warn("User not found by id: {}", userDTO.getId());
            return new UserNotFoundException();
        });
        return user;
    }
}