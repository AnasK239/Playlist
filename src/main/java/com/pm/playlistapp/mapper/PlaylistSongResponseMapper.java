package com.pm.playlistapp.mapper;

import com.pm.playlistapp.dto.PlaylistSongResponse;
import com.pm.playlistapp.models.PlaylistSong;
import com.pm.playlistapp.models.Song;
import org.springframework.stereotype.Component;


@Component
public class PlaylistSongResponseMapper {

    public PlaylistSongResponse toSongResponse(PlaylistSong playlistSong) {

        Song song = playlistSong.getSong();

        return PlaylistSongResponse.builder()
                .songId(song.getId().toString())
                .title(song.getTitle())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .genre(song.getGenre())
                .addedAt(playlistSong.getAddedAt())
                .position(playlistSong.getPosition())
                .build();
    }
}
