package com.hanghae.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Data
@NoArgsConstructor
@Table(name = "playback")
public class Playback {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String userId;

  @Column(nullable = false, unique = true)
  private String videoId;

  @Column
  private Duration lastPlayPoint = Duration.ZERO;

  @Column
  private LocalDateTime lastPlayedAt;

  @Column
  private int adViewCount = 0;

  public Playback(String videoId, String userId, LocalDateTime lastPlayedAt) {
    this.videoId = videoId;
    this.userId = userId;
    this.lastPlayedAt = lastPlayedAt;
  }

  public void updateLastPlayedAt () {
    this.lastPlayedAt = LocalDateTime.now();
  }

  public void updateLastPlayPoint(Duration playPoint) {
    this.lastPlayPoint = playPoint;
  }
}
