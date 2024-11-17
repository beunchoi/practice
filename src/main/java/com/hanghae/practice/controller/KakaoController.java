package com.hanghae.practice.controller;

import com.hanghae.practice.dto.KakaoTokenResponseDto;
import com.hanghae.practice.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users/kakao")
@RequiredArgsConstructor
public class KakaoController {

  private final KakaoService kakaoService;

  @Value("${kakao.client_id}")
  private String clientId;

  @Value("${kakao.redirect_uri}")
  private String redirectUri;

  @GetMapping("/login/page")
  public String loginPage(Model model) {
    String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUri;
    model.addAttribute("location", location);

    return "login";
  }

  @GetMapping("/callback")
  public ResponseEntity<KakaoTokenResponseDto> callback(@RequestParam("code") String code) {
    KakaoTokenResponseDto responseDto = kakaoService.getAccessTokenFromKakao(code);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }
}
