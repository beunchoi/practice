package com.practice.userservice.controller;

import com.practice.userservice.common.dto.ResponseMessage;
import com.practice.userservice.dto.LoginReqDto;
import com.practice.userservice.dto.SignupReqDto;
import com.practice.userservice.entity.User;
import com.practice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<ResponseMessage> signup(@RequestBody SignupReqDto reqDto) {
    User createdUser = userService.signup(reqDto);

    ResponseMessage message = ResponseMessage.builder()
        .data(createdUser)
        .statusCode(201)
        .resultMessage("회원 가입 성공")
        .build();

    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseMessage> login(@RequestBody LoginReqDto reqDto) {
    String token = userService.login(reqDto);

    ResponseMessage message = ResponseMessage.builder()
        .data(token)
        .statusCode(200)
        .resultMessage("로그인 성공")
        .build();

    return ResponseEntity.status(HttpStatus.OK).body(message);
  }
}
