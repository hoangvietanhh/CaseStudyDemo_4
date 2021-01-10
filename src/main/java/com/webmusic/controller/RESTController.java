package com.webmusic.controller;

import com.webmusic.model.Song;
import com.webmusic.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RESTController {
    @Autowired
    SongServiceImpl songService;

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> listSong(){
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> getSong(@PathVariable("id") long id) {
        Song song = songService.findById(id);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/", method = RequestMethod.POST)
    public ResponseEntity<Void> createSong(@RequestBody Song song, UriComponentsBuilder ucBuilder) {
        songService.save(song);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/songs/{id}").buildAndExpand(song.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Song> updateSong(@PathVariable("id") long id, @RequestBody Song song) {
        Song currentSong = songService.findById(id);
        currentSong.setName(song.getName());
        currentSong.setLyric(song.getLyric());
        currentSong.setUrl(song.getUrl());
        currentSong.setStatus(song.isStatus());
        currentSong.setCreate_at(song.getCreate_at());

        songService.save(currentSong);
        return new ResponseEntity<>(currentSong, HttpStatus.OK);
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Song> deleteSong(@PathVariable("id") long id) {
        Song song = songService.findById(id);
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
