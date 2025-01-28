package com.practice.userservice.dto;

import lombok.Getter;

@Getter
public class SignupReqDto {
  private String email;
  private String password;
  private String name;
  private String phoneNum;
  private String producerToken;
}
