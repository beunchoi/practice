package com.hanghae.practice.service;

import com.hanghae.practice.dto.PlaybackRequestDto;
import com.hanghae.practice.dto.PlaybackResponseDto;
import com.hanghae.practice.dto.VideoRequestDto;
import com.hanghae.practice.dto.VideoResponseDto;

public interface VideoService {

  VideoResponseDto addVideo(VideoRequestDto videoRequestDto);
  PlaybackResponseDto playVideo(String videoId, String userId);
  PlaybackResponseDto stopVideo(String videoId, String userId);
}
