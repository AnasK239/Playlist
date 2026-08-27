package com.pm.playlistapp.service;

import com.pm.playlistapp.dto.CreatePlaylistRequest;
import com.pm.playlistapp.dto.PageResponse;
import com.pm.playlistapp.dto.PlaylistResponse;


import java.util.UUID;


public interface IPlaylistService {

    PlaylistResponse createPlaylist(UUID userId  , CreatePlaylistRequest request);

    void addSongToPlaylist(UUID userId, UUID playlistId, UUID songId);

    PageResponse<PlaylistResponse> getUserPlaylists(UUID userId, int page, int size);

}
