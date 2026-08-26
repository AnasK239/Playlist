package com.pm.playlistapp.service.impl;

import com.pm.playlistapp.dto.CreatePlaylistRequest;
import com.pm.playlistapp.dto.PlaylistResponse;
import com.pm.playlistapp.exception.ForbiddenOperationException;
import com.pm.playlistapp.exception.ResourceNotFoundException;
import com.pm.playlistapp.mapper.PlaylistResponseMapper;
import com.pm.playlistapp.models.Playlist;
import com.pm.playlistapp.models.PlaylistSong;
import com.pm.playlistapp.models.Song;
import com.pm.playlistapp.models.User;
import com.pm.playlistapp.repository.PlaylistRepository;
import com.pm.playlistapp.repository.PlaylistSongRepository;
import com.pm.playlistapp.repository.SongRepository;
import com.pm.playlistapp.repository.UserRepository;
import com.pm.playlistapp.service.IPlaylistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PlaylistServiceImpl implements IPlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final PlaylistSongRepository playlistSongRepository;

    private final PlaylistResponseMapper playlistResponseMapper;


    public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserRepository userRepository, SongRepository songRepository, PlaylistSongRepository playlistSongRepository, PlaylistResponseMapper playlistResponseMapper) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playlistSongRepository = playlistSongRepository;
        this.playlistResponseMapper = playlistResponseMapper;
    }

    @Override
    @Transactional
    public PlaylistResponse createPlaylist(UUID userId, CreatePlaylistRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User" , userId)
        );

        Playlist playlist = new Playlist(user , request.playlistName());

        Playlist savedPlaylist = playlistRepository.save(playlist);

        return playlistResponseMapper.toPlaylistResponse(savedPlaylist);
    }

    @Override
    @Transactional
    public void addSongToPlaylist(UUID userId, UUID playlistId, UUID songId) {

        Playlist playlist = playlistRepository.findByIdForUpdate(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist", playlistId));

        if (!playlist.getUser().getId().equals(userId)) {
            throw new ForbiddenOperationException("You do not have permission to modify this playlist");
        }

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song" , songId));

        Integer maxPosition = playlistSongRepository.findMaxPositionByPlaylistId(playlistId);
        int nextPosition = (maxPosition == null) ? 1 : maxPosition + 1;

        PlaylistSong playlistSong = new PlaylistSong(playlist, song, nextPosition);
        playlistSongRepository.save(playlistSong);
    }

}
