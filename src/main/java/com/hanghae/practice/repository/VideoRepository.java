package com.hanghae.practice.repository;

import com.hanghae.practice.entity.Video;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
  Optional<Video> findByVideoId(String videoId);
}
