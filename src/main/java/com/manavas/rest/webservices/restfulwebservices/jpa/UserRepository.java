package com.manavas.rest.webservices.restfulwebservices.jpa;

import com.manavas.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);
    List<User> findByBirthDate(Date birthDate);
}
