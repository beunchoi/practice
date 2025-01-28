package com.practice.userservice.service;

import com.practice.userservice.common.exception.BizRuntimeException;
import com.practice.userservice.dto.LoginReqDto;
import com.practice.userservice.dto.SignupReqDto;
import com.practice.userservice.entity.User;
import com.practice.userservice.entity.UserRoleEnum;
import com.practice.userservice.repository.UserRepository;
import com.practice.userservice.util.JwtUtil;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  @Value("${producer.token}")
  private String PRODUCER_TOKEN;

  @Override
  public User signup(SignupReqDto reqDto) {
    if (userRepository.findByEmail(reqDto.getEmail()).isPresent()) {
      throw new BizRuntimeException("중복된 이메일입니다.");
    }

    if (!isPasswordValid(reqDto.getPassword())) {
      throw new BizRuntimeException("조건에 맞지 않는 비밀번호입니다.");
    }

    String userId = UUID.randomUUID().toString();
    String password = passwordEncoder.encode(reqDto.getPassword());
    UserRoleEnum role = UserRoleEnum.USER;
    if (PRODUCER_TOKEN.equals(reqDto.getProducerToken())) {
      role = UserRoleEnum.PRODUCER;
    }

    return userRepository.save(new User(userId, reqDto, password, role));
  }

  @Override
  public String login(LoginReqDto reqDto) {
    return null;
  }

  private boolean isPasswordValid(String password) {
    String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*?_])[A-Za-z\\d!@#$%^&*?_]{8,16}$";
    return password.matches(passwordPattern);
  }
}
