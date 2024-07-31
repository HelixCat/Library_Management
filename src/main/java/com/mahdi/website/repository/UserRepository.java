package com.mahdi.website.repository;

import com.mahdi.website.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :userName")
    User findByUserName(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newHashedPassword WHERE u.username = :username")
    Integer updateUserPassword(@Param("username") String username, @Param("newHashedPassword") String newHashedPassword);
}
