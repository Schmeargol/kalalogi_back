package com.fishlog.kalalogi_back.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1 and u.password = ?2 and u.status = ?3")
    Optional<User> findUser(String username, String password, String status);

    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean userExists(String username);


}