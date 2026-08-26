package com.pm.playlistapp.repository;

import com.pm.playlistapp.models.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, UUID> {
}
