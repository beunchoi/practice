package com.practice.videoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoRequestDto {

  private String title;
  private String userId;
  private long totalPlayTime;
}
