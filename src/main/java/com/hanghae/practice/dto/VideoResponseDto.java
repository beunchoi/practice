package com.hanghae.practice.dto;

import com.hanghae.practice.entity.Video;
import java.time.Duration;
import lombok.Getter;

@Getter
public class VideoResponseDto {

  private String videoId;
  private String title;
  private String userId;
  private String url;
  private Duration totalPlayTime;

  public VideoResponseDto(Video video) {
    this.videoId = video.getVideoId();
    this.title = video.getTitle();
    this.userId = video.getUserId();
    this.url = video.getUrl();
    this.totalPlayTime = video.getTotalPlayTime();
  }
}
