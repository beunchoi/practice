package com.hanghae.practice.dto;

import java.time.Duration;
import lombok.Getter;

@Getter
public class VideoRequestDto {

  private String title;
  private String userId;
  private String url;
  private Duration totalPlayTime;
}
