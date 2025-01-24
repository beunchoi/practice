package com.hanghae.practice.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.hanghae.practice.dto.VideoRequestDto;
import com.hanghae.practice.dto.VideoResponseDto;
import com.hanghae.practice.entity.Video;
import com.hanghae.practice.repository.PlaybackRepository;
import com.hanghae.practice.repository.VideoRepository;
import java.util.UUID;
import org.assertj.core.api.Assertions;
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
//  @BeforeEach
//  void beforeEach() {
//    videoService = new VideoServiceImpl(videoRepository, playbackRepository);
//  }

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
    Assertions.assertThat(responseDto.getVideoId()).isEqualTo(videoId);
  }

  @Test
  void playVideo() {
    // given

    // when

    // then
  }

  @Test
  void stopVideo() {
    // given

    // when

    // then
  }
}