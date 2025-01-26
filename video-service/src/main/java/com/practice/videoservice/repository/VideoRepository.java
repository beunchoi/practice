package com.practice.videoservice.repository;

import com.practice.videoservice.entity.Video;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
  Optional<Video> findByVideoId(String videoId);
}
