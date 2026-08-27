package com.pm.playlistapp.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record PlaylistSongResponse(
        String songId,
        String title,
        String artist,
        String album,
        String genre,
        Integer position,
        Instant addedAt
) {
}