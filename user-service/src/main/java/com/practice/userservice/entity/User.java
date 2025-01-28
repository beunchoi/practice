package com.practice.userservice.entity;

import com.practice.userservice.dto.SignupReqDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String userId;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false, unique = true)
  private String phoneNum;

  @Column(nullable = false)
  private String profile = "자기소개 해주세요";

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;


  public User(String userId, SignupReqDto reqDto, String password, UserRoleEnum role) {
    this.userId = userId;
    this.email = reqDto.getEmail();
    this.password = password;
    this.name = reqDto.getName();
    this.phoneNum = reqDto.getPhoneNum();
    this.role = role;
  }

  public void updateProfile(String profile) {
    this.profile = profile;
  }
}
