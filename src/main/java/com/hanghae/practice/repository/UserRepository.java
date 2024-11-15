package com.hanghae.practice.repository;

import com.hanghae.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
