package br.com.listenermusic.repository;

import br.com.listenermusic.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicRepository extends MongoRepository<Music, UUID> {

    Music findById(String id);
}