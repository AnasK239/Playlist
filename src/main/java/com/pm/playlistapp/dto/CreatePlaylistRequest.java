package com.pm.playlistapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePlaylistRequest (
        @NotBlank(message = "Playlist name is required")
        @Size(max = 100, message = "Playlist name must not exceed 100 characters")
        String playlistName
){
}
