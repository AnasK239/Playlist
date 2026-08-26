package com.pm.playlistapp.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.*;

@Entity
@Table(name = "songs")
@Getter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    @Column(nullable = false)
    private String genre;

    @OneToMany(mappedBy = "song")
    private List<PlaylistSong> playlistAppearances = new ArrayList<>();
}
