package com.practice.userservice.service;

import com.practice.userservice.dto.LoginReqDto;
import com.practice.userservice.dto.SignupReqDto;
import com.practice.userservice.entity.User;

public interface UserService {
  User signup(SignupReqDto reqDto);
  String login(LoginReqDto reqDto);
}
