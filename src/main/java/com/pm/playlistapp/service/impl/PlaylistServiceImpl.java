package com.pm.playlistapp.service.impl;

import com.pm.playlistapp.dto.CreatePlaylistRequest;
import com.pm.playlistapp.dto.PlaylistResponse;
import com.pm.playlistapp.exception.ResourceNotFoundException;
import com.pm.playlistapp.mapper.PlaylistResponseMapper;
import com.pm.playlistapp.models.Playlist;
import com.pm.playlistapp.models.User;
import com.pm.playlistapp.repository.PlaylistRepository;
import com.pm.playlistapp.repository.UserRepository;
import com.pm.playlistapp.service.IPlaylistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PlaylistServiceImpl implements IPlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    private final PlaylistResponseMapper playlistResponseMapper;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserRepository userRepository, PlaylistResponseMapper playlistResponseMapper) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
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

}
