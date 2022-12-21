package com.microservice.users.repository;

import com.microservice.users.entities.Rating;
import com.microservice.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

}
