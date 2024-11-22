package com.hanghae.practice.dto;

import com.hanghae.practice.entity.Playback;
import java.time.Duration;
import lombok.Getter;

@Getter
public class PlaybackResponseDto {
  private String videoId;
  private String userId;
  private Duration lastPlayPoint;

  public PlaybackResponseDto(Playback playback) {
    this.videoId = playback.getVideoId();
    this.userId = playback.getUserId();
    this.lastPlayPoint = playback.getLastPlayPoint();
  }
}
