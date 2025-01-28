package com.practice.userservice.util;

import com.practice.userservice.entity.UserRoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

  public static final String BEAR_PREFIX = "Bearer ";
  public static final String AUTHORIZATION_KEY = "auth";
  private final long ACCESS_TOKEN_TIME = 60 * 60 * 1000L;

  @Value("${jwt.secret.key}")
  private String secretKey;
  private Key key;
  private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  @PostConstruct
  public void init() {
    byte[] bytes = Base64.getDecoder().decode(secretKey);
    key = Keys.hmacShaKeyFor(bytes);
  }

  public String createAccessToken(String userId, UserRoleEnum role) {
    Date date = new Date();

    return BEAR_PREFIX +
        Jwts.builder()
          .setSubject(userId)
          .claim(AUTHORIZATION_KEY, role) // 사용자 권한
          .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_TIME)) // 만료 시간
          .setIssuedAt(date) // 발급일
          .signWith(key, signatureAlgorithm) // 암호화 알고리즘
          .compact();
  }
}
