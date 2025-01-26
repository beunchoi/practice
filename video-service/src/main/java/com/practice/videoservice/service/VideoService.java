package com.practice.videoservice.service;

import com.practice.videoservice.dto.PlaybackResponseDto;
import com.practice.videoservice.dto.VideoRequestDto;
import com.practice.videoservice.dto.VideoResponseDto;

public interface VideoService {

  VideoResponseDto addVideo(VideoRequestDto videoRequestDto);
  PlaybackResponseDto playVideo(String videoId, String userId);
  PlaybackResponseDto stopVideo(String videoId, String userId);
}
