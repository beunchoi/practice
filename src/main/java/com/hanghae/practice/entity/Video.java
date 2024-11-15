package com.hanghae.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Duration;
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
  private String url;

  @Column
  private Duration totalPlayTime;

  @Column
  private Long views;
}
