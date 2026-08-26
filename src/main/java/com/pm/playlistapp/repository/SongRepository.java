package com.pm.playlistapp.repository;

import com.pm.playlistapp.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}
