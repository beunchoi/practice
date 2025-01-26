package com.practice.videoservice.service;

import com.practice.videoservice.dto.PlaybackResponseDto;
import com.practice.videoservice.dto.VideoRequestDto;
import com.practice.videoservice.dto.VideoResponseDto;
import com.practice.videoservice.entity.Playback;
import com.practice.videoservice.entity.Video;
import com.practice.videoservice.repository.PlaybackRepository;
import com.practice.videoservice.repository.VideoRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {

  private final VideoRepository videoRepository;
  private final PlaybackRepository playbackRepository;

  @Override
  public VideoResponseDto addVideo(VideoRequestDto videoRequestDto) {
    String videoId = UUID.randomUUID().toString();

    Video savedVideo = videoRepository.save(new Video(videoId, videoRequestDto));

    return new VideoResponseDto(savedVideo);
  }

  @Transactional
  @Override
  public PlaybackResponseDto playVideo(String videoId, String userId) {
    Video video = videoRepository.findByVideoId(videoId)
        .orElseThrow(() -> new IllegalArgumentException("동영상이 존재하지 않습니다."));

    Playback playback = playbackRepository.findByVideoIdAndUserId(video.getVideoId(), userId)
        .orElseGet(() -> new Playback(video.getVideoId(), userId, System.currentTimeMillis()));

    playback.updateLastPlayedAt();
    playbackRepository.save(playback);
    video.increaseViews();

    return new PlaybackResponseDto(playback);
  }

  @Transactional
  @Override
  public PlaybackResponseDto stopVideo(String videoId, String userId) {
    Playback playback = playbackRepository.findByVideoIdAndUserId(
            videoId, userId)
        .orElseThrow(() -> new IllegalArgumentException("재생 정보가 존재하지 않습니다."));

    long playTime = System.currentTimeMillis() - playback.getStartTime();

    playback.updateLastPlayPoint(playTime);

    return new PlaybackResponseDto(playback);
  }
}
