package com.practice.videoservice.controller;

import com.practice.videoservice.dto.PlaybackResponseDto;
import com.practice.videoservice.dto.VideoRequestDto;
import com.practice.videoservice.dto.VideoResponseDto;
import com.practice.videoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @PostMapping
  public ResponseEntity<?> addVideo(@RequestBody VideoRequestDto videoRequestDto) {
    VideoResponseDto responseDto = videoService.addVideo(videoRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @PostMapping("/play")
  public ResponseEntity<?> playVideo(@RequestParam String videoId, @RequestParam String userId) {
    PlaybackResponseDto responseDto = videoService.playVideo(videoId, userId);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @PatchMapping("/stop")
  public ResponseEntity<?> stopVideo(@RequestParam String videoId, @RequestParam String userId) {
    PlaybackResponseDto responseDto = videoService.stopVideo(videoId, userId);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }
}
