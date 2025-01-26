package com.practice.videoservice.dto;

import com.practice.videoservice.entity.Playback;
import lombok.Getter;

@Getter
public class PlaybackResponseDto {
  private String videoId;
  private String userId;
  private long lastPlayPoint;

  public PlaybackResponseDto(Playback playback) {
    this.videoId = playback.getVideoId();
    this.userId = playback.getUserId();
    this.lastPlayPoint = playback.getLastPlayPoint();
  }
}
