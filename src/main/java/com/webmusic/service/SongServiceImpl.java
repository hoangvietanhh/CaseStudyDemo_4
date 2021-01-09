package com.webmusic.service;

import com.webmusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService<Song> {

    @Autowired
    SongRepository songRepository;


    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public Song findById(Long id) {
        return null;
    }

    @Override
    public Song save(Song obj) {
        return null;
    }
}
