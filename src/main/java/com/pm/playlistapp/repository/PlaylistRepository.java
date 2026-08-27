package com.pm.playlistapp.repository;

import com.pm.playlistapp.models.Playlist;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT playlist
            FROM Playlist playlist
            WHERE playlist.id = :playlistId
            """)
    Optional<Playlist> findByIdForUpdate(@Param("playlistId") UUID playlistId);

    Page<Playlist> findByUserId(UUID userId, Pageable pageable);
}
