package com.pm.playlistapp.controller;

import com.pm.playlistapp.dto.CreatePlaylistRequest;
import com.pm.playlistapp.dto.PageResponse;
import com.pm.playlistapp.dto.PlaylistResponse;
import com.pm.playlistapp.dto.PlaylistSongResponse;
import com.pm.playlistapp.service.IPlaylistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final IPlaylistService playlistService;

    public PlaylistController(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<PlaylistResponse> createPlaylist(
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody CreatePlaylistRequest request
    ) {
        PlaylistResponse response = playlistService.createPlaylist(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> addSongToPlaylist(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID playlistId,
            @PathVariable UUID songId
    ) {
        playlistService.addSongToPlaylist(userId, playlistId, songId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<PlaylistResponse>> getUserPlaylists(
            @RequestHeader("X-User-Id") UUID userId,

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page must be zero or greater")
            int page,

            @RequestParam(defaultValue = "20")
            @Min(value = 1, message = "Size must be at least 1")
            @Max(value = 100, message = "Size must not exceed 100")
            int size
    ) {
        PageResponse<PlaylistResponse> response = playlistService.getUserPlaylists(userId, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<PageResponse<PlaylistSongResponse>> getPlaylistSongs(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID playlistId,

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page must be zero or greater")
            int page,

            @RequestParam(defaultValue = "20")
            @Min(value = 1, message = "Size must be at least 1")
            @Max(value = 100, message = "Size must not exceed 100")
            int size
    ) {

        PageResponse<PlaylistSongResponse> response = playlistService.getPlaylistSongs(
                userId,
                playlistId,
                page,
                size
        );
        return ResponseEntity.ok(response);
    }
}
