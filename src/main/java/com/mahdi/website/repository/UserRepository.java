package com.mahdi.website.repository;

import com.mahdi.website.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUserName(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    Optional<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.nationalCode = :nationalCode")
    Optional<User> findByNationalCode(@Param("nationalCode") String nationalCode);

    @Modifying
    @Query("UPDATE User u SET u.password = :newHashedPassword WHERE u.username = :username")
    Integer updateUserPassword(@Param("username") String username, @Param("newHashedPassword") String newHashedPassword);
}
