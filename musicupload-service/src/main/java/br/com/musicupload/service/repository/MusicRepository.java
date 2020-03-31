package br.com.musicupload.service.repository;

import br.com.musicupload.service.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicRepository extends MongoRepository<Music, UUID> {
}