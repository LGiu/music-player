package br.com.listenermusic.service;

import br.com.global.service.ServiceGeneric;
import br.com.listenermusic.model.Music;
import br.com.listenermusic.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MusicService extends ServiceGeneric<Music> {

    @Autowired
    public MusicService() {
        super(Music.class);
    }

    public Music getById(MusicRepository musicRepository, String id) {
        Music music = musicRepository.findById(id);
        return music;
    }

}
