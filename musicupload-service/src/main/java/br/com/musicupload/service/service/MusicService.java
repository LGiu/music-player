package br.com.musicupload.service.service;

import br.com.global.Util.Return;
import br.com.global.service.ServiceGeneric;
import br.com.musicupload.service.model.Music;
import br.com.musicupload.service.repository.MusicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class MusicService extends ServiceGeneric<Music> {

    private MusicRepository musicRepository;

    @Value("${kafka.header.id-customer")
    String idMusic;

    @Value("${kafka.header.name-music")
    String nameMusic;

    @Value("${kafka.header.size-music")
    String sizeMusic;

    @Autowired
    public MusicService(MusicRepository musicRepository) {
        super(Music.class);
        this.musicRepository = musicRepository;
    }

    @KafkaListener(topics = "${kafka.topic.request-topic}", groupId = "${kafka.consumer.group-id}")
    public void listen(@Payload ConsumerRecord record,
                       @Headers MessageHeaders messageHeaders) {

        try{
            Music music = new Music();
            music.setIdCustomer(new String((byte[]) messageHeaders.get(idMusic), UTF_8));
            music.setName(new String((byte[]) messageHeaders.get(nameMusic), UTF_8));
            music.setFile(new Binary(BsonBinarySubType.BINARY, ((Bytes) record.value()).get()));
            music.setSize(Long.parseLong(new String((byte[]) messageHeaders.get(sizeMusic), UTF_8)));
            Return aReturn = super.save(musicRepository, music);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
