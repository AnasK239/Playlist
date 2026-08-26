package com.pm.playlistapp.controller;

import com.pm.playlistapp.dto.CreatePlaylistRequest;
import com.pm.playlistapp.dto.PlaylistResponse;
import com.pm.playlistapp.service.IPlaylistService;
import jakarta.validation.Valid;
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






}
