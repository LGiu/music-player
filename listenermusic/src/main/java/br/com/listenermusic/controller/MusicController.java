package br.com.listenermusic.controller;


import br.com.listenermusic.model.Music;
import br.com.listenermusic.repository.MusicRepository;
import br.com.listenermusic.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
public class MusicController {

    private final MusicService musicService;
    private final MusicRepository musicRepository;

    @Autowired
    public MusicController(MusicService musicService, MusicRepository musicRepository) {
        this.musicService = musicService;
        this.musicRepository = musicRepository;
    }

    @GetMapping(value = "/music-listener/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> musicListener(@PathVariable("id") String id) {
        Music music = musicService.getById(musicRepository, id);

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(music.getFile().getData()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(music.getSize())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"music.mp3")
                .body(resource);
    }

}