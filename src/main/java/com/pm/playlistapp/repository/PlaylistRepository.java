package com.pm.playlistapp.repository;

import com.pm.playlistapp.models.Playlist;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT playlist
            FROM Playlist playlist
            WHERE playlist.id = :playlistId
            """)
    Optional<Playlist> findByIdForUpdate(@Param("playlistId") UUID playlistId);


}
