package com.user.service.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.service.UserService.entities.User;

@Repository
public interface userRepository extends JpaRepository<User , String> {

}
