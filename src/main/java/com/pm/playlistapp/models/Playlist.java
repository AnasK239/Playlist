package com.pm.playlistapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(
        name = "playlists",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_user_playlist_name", columnNames = {"user_id", "name"}
        )
)
@NoArgsConstructor
@Getter
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaylistSong> tracks = new ArrayList<>();

    public Playlist(User user, String name) {
        this.user = user;
        this.name = name;
    }

}
