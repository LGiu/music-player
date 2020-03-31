package br.com.musicupload.api.service;

import br.com.global.Util.Logs;
import br.com.global.Util.Return;
import br.com.global.service.ServiceGeneric;
import br.com.musicupload.api.DTO.MusicDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MusicService extends ServiceGeneric<MusicDTO> {

    private final KafkaTemplate kafkaTemplate;

    @Value("${kafka.header.id-customer")
    String idCutomer;

    @Value("${kafka.header.name-music")
    String nameMusic;

    @Value("${kafka.header.size-music")
    String sizeMusic;

    @Autowired
    public MusicService(KafkaTemplate kafkaTemplate) {
        super(MusicDTO.class);
        this.kafkaTemplate = kafkaTemplate;
    }

    public Return execute(MusicDTO musicDTO, String requestTopic, String requestReplyTopic) {
        Bytes bytes = null;
        try {
            bytes = new Bytes(musicDTO.getFile().getBytes());
        } catch (IOException e) {
            Logs.error(this.getClass(), e.getMessage());
        }

        ProducerRecord<Bytes, Bytes> producerRecord = new ProducerRecord(requestTopic, bytes, bytes);
        producerRecord.headers().add(new RecordHeader(idCutomer, musicDTO.getIdCustomer().getBytes()));
        producerRecord.headers().add(new RecordHeader(nameMusic, musicDTO.getName().getBytes()));
        producerRecord.headers().add(new RecordHeader(sizeMusic, musicDTO.getSize().toString().getBytes()));
        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        kafkaTemplate.send(producerRecord);

        return new Return();
    }
}
