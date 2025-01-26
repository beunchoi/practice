package com.practice.videoservice.dto;

import com.practice.videoservice.entity.Video;
import lombok.Getter;

@Getter
public class VideoResponseDto {

  private String videoId;
  private String title;
  private String userId;
  private long totalPlayTime;

  public VideoResponseDto(Video video) {
    this.videoId = video.getVideoId();
    this.title = video.getTitle();
    this.userId = video.getUserId();
    this.totalPlayTime = video.getTotalPlayTime();
  }
}
