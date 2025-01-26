package com.practice.service;

import com.practice.dto.KakaoTokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {

  private final RestTemplate restTemplate;

  @Value("${kakao.client_id}")
  private String clientId;
  private static final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
  private static final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

  public KakaoTokenResponseDto getAccessTokenFromKakao(String code) {

    // 요청 URL 생성
    String url = UriComponentsBuilder.fromHttpUrl(KAUTH_TOKEN_URL_HOST)
        .path("/oauth/token")
        .queryParam("grant_type", "authorization_code")
        .queryParam("client_id", clientId)
        .queryParam("code", code)
        .toUriString();

    // HTTP 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // HTTP 요청 생성 (Body가 없는 POST 요청)
    HttpEntity<String> entity = new HttpEntity<>(headers);

    try {
      // POST 요청 실행 및 응답 받기
      ResponseEntity<KakaoTokenResponseDto> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          entity,
          KakaoTokenResponseDto.class
      );

      // 응답 상태 코드 확인
      if (response.getStatusCode().is2xxSuccessful()) {
        log.info(" [Kakao Service] Access Token ------> {}", response.getBody().getAccessToken());

        return response.getBody(); // 정상 응답 처리
      } else {
        throw new RuntimeException("Unexpected status code: " + response.getStatusCode());
      }

    } catch (Exception e) {
      // Custom Exception 처리
      if (e instanceof org.springframework.web.client.HttpClientErrorException) {
        throw new RuntimeException("Invalid Parameter");
      } else if (e instanceof org.springframework.web.client.HttpServerErrorException) {
        throw new RuntimeException("Internal Server Error");
      } else {
        throw new RuntimeException("Unknown Error", e);
      }
    }
  }
}