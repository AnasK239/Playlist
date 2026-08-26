package com.pm.playlistapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "playlist_songs",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_playlist_song",
                        columnNames = {"playlist_id", "song_id"}
                ),
                @UniqueConstraint(
                        name = "uk_playlist_position",
                        columnNames = {"playlist_id" , "position"}
                )
        }
)
@NoArgsConstructor
@Getter
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(nullable = false)
    private Integer position;

    private Instant addedAt;

    public PlaylistSong(Playlist playlist, Song song, Integer position) {
        this.playlist = playlist;
        this.song = song;
        this.position = position;
    }

    @PrePersist
    public void onCreate() {
        addedAt = Instant.now();
    }
}
