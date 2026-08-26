package com.pm.playlistapp.repository;

import com.pm.playlistapp.models.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, UUID> {

    @Query("SELECT COALESCE(MAX(ps.position), 0) FROM PlaylistSong ps WHERE ps.playlist.id = :playlistId")
    Integer findMaxPositionByPlaylistId(@Param("playlistId") UUID playlistId);


}
