package com.hanghae.practice.repository;

import com.hanghae.practice.entity.Playback;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaybackRepository extends JpaRepository<Playback, Long> {
  Optional<Playback> findByVideoIdAndUserId(String videoId, String userId);
}
