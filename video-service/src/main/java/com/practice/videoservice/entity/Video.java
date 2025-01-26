package com.practice.videoservice.entity;

import com.practice.videoservice.dto.VideoRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Data
@NoArgsConstructor
@Table(name = "video")
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String videoId;

  @Column
  private String title;

  @Column
  private String userId;

  @Column
  private long totalPlayTime;

  @Column
  private long views;

  public Video(String videoId, VideoRequestDto videoRequestDto) {
    this.videoId = videoId;
    this.title = videoRequestDto.getTitle();
    this.userId = videoRequestDto.getUserId();
    this.totalPlayTime = videoRequestDto.getTotalPlayTime();
  }

  public void increaseViews() {
    this.views++;
  }
}
