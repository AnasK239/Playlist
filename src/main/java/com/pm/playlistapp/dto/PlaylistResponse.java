package com.pm.playlistapp.dto;

import lombok.Builder;

@Builder
public record PlaylistResponse(
        String playlistName,
        String playlistId
) {
}
