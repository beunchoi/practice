package com.practice.videoservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.practice.videoservice.dto.PlaybackResponseDto;
import com.practice.videoservice.dto.VideoRequestDto;
import com.practice.videoservice.dto.VideoResponseDto;
import com.practice.videoservice.entity.Playback;
import com.practice.videoservice.entity.Video;
import com.practice.videoservice.repository.PlaybackRepository;
import com.practice.videoservice.repository.VideoRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

  @Mock
  private VideoRepository videoRepository;
  @Mock
  private PlaybackRepository playbackRepository;
  @InjectMocks
  private VideoServiceImpl videoService;

  @Test
  @DisplayName("동영상 저장")
  void addVideo() {
    // given
    String videoId = UUID.randomUUID().toString();
    VideoRequestDto videoRequestDto = new VideoRequestDto("제목", "123-234", 10000);
    Video savedvideo = new Video(videoId, videoRequestDto);
    when(videoRepository.save(any(Video.class))).thenReturn(savedvideo);
    // when
    VideoResponseDto responseDto = videoService.addVideo(videoRequestDto);
    // then
    assertThat(responseDto.getVideoId()).isEqualTo(videoId);
  }

  @Test
  @DisplayName("재생 기록이 있는 경우의 동영상 재생")
  void existingPlaybackPlayVideo() {
    // given
    String videoId = "123-456";
    String userId = "234-567";
    VideoRequestDto videoRequestDto = new VideoRequestDto("제목", userId, 10000);
    Video video = new Video(videoId, videoRequestDto);
    Playback existingPlayback = new Playback(videoId, userId, 10000);

    when(videoRepository.findByVideoId(videoId)).thenReturn(Optional.of(video));
    when(playbackRepository.findByVideoIdAndUserId(videoId, userId)).thenReturn(Optional.of(existingPlayback));

    // when
    PlaybackResponseDto responseDto = videoService.playVideo(videoId, userId);
    // then
    assertThat(responseDto.getVideoId()).isEqualTo(videoId);
  }

  @Test
  @DisplayName("재생 기록이 없는 경우의 동영상 재생")
  void newPlaybackPlayVideo() {
    // given
    String videoId = "123-456";
    String userId = "234-567";
    VideoRequestDto videoRequestDto = new VideoRequestDto("제목", userId, 10000);
    Video video = new Video(videoId, videoRequestDto);
    Playback newPlayback = new Playback(videoId, userId, 10000);

    when(videoRepository.findByVideoId(videoId)).thenReturn(Optional.of(video));
    when(playbackRepository.findByVideoIdAndUserId(videoId, userId)).thenReturn(Optional.empty());
    when(playbackRepository.save(any(Playback.class))).thenReturn(newPlayback);

    // when
    PlaybackResponseDto responseDto = videoService.playVideo(videoId, userId);
    // then
    assertThat(responseDto.getVideoId()).isEqualTo(videoId);
  }

  @Test
  @DisplayName("동영상 재생 중지")
  void stopVideo() {
    // given
    String videoId = "123-456";
    String userId = "234-567";
    Playback playback = new Playback(videoId, userId, System.currentTimeMillis());

    when(playbackRepository.findByVideoIdAndUserId(videoId, userId)).thenReturn(Optional.of(playback));
    // when
    PlaybackResponseDto responseDto = videoService.stopVideo(videoId, userId);
    // then
    assertThat(responseDto.getLastPlayPoint()).isNotEqualTo(0);
  }
}