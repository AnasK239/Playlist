package com.pm.playlistapp.mapper;

import com.pm.playlistapp.dto.PlaylistResponse;
import com.pm.playlistapp.models.Playlist;
import org.springframework.stereotype.Component;

@Component
public class PlaylistResponseMapper {

    public PlaylistResponse toPlaylistResponse(Playlist playlist) {

        return PlaylistResponse.builder()
                .playlistId(playlist.getId().toString())
                .playlistName(playlist.getName())
                .build();
    }
}
