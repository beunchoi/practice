package com.hanghae.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(clientHttpRequestFactory());
  }

  private ClientHttpRequestFactory clientHttpRequestFactory() {
    // 요청 및 읽기 타임아웃 설정
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(5000); // 연결 타임아웃 (ms)
    factory.setReadTimeout(5000);    // 읽기 타임아웃 (ms)
    return factory;
  }
}
