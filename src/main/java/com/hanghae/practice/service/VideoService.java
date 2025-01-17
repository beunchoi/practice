package com.hanghae.practice.service;

import com.hanghae.practice.dto.PlaybackRequestDto;
import com.hanghae.practice.dto.PlaybackResponseDto;
import com.hanghae.practice.dto.VideoRequestDto;
import com.hanghae.practice.dto.VideoResponseDto;
import com.hanghae.practice.entity.Playback;
import com.hanghae.practice.entity.Video;
import com.hanghae.practice.repository.PlaybackRepository;
import com.hanghae.practice.repository.VideoRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoService {

  private final VideoRepository videoRepository;
  private final PlaybackRepository playbackRepository;

  public VideoResponseDto addVideo(VideoRequestDto videoRequestDto) {
    String videoId = UUID.randomUUID().toString();

    Video savedVideo = videoRepository.save(new Video(videoId, videoRequestDto));

    return new VideoResponseDto(savedVideo);
  }

  @Transactional
  public void playVideo(String videoId, String userId) {
    Video video = videoRepository.findByVideoId(videoId)
        .orElseThrow(() -> new IllegalArgumentException("동영상이 존재하지 않습니다."));

    Playback playback = playbackRepository.findByVideoIdAndUserId(video.getVideoId(), userId)
        .orElseGet(() -> new Playback(video.getVideoId(), userId, System.currentTimeMillis()));

    playback.updateLastPlayedAt();
    playbackRepository.save(playback);
    video.increaseViews();
  }

  @Transactional
  public PlaybackResponseDto stopVideo(PlaybackRequestDto requestDto) {
    Playback playback = playbackRepository.findByVideoIdAndUserId(
        requestDto.getVideoId(), requestDto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("재생 정보가 존재하지 않습니다."));

    long playTime = System.currentTimeMillis() - playback.getStartTime();

    playback.updateLastPlayPoint(playTime);

    return new PlaybackResponseDto(playback);
  }
}
