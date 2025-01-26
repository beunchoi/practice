package com.practice.videoservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  private long lastPlayPoint;

  @Column
  private long startTime;

  @Column
  private LocalDateTime lastPlayedAt;

  @Column
  private int adViewCount;

  public Playback(String videoId, String userId, long startTime) {
    this.videoId = videoId;
    this.userId = userId;
    this.startTime = startTime;
  }

  public void updateLastPlayedAt () {
    this.lastPlayedAt = LocalDateTime.now();
  }

  public void updateLastPlayPoint(long playTime) {
    this.lastPlayPoint += playTime;
  }
}
