package br.com.customer.api.service;

import br.com.customer.api.DTO.CustomerDTO;
import br.com.global.Util.Return;
import br.com.global.service.ServiceGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CustomerService extends ServiceGeneric<CustomerDTO> {

    @Autowired
    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    public CustomerService() {
        super(CustomerDTO.class);
    }

    public Return execute(CustomerDTO customerDTO, String requestTopic, String requestReplyTopic) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(customerDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // montando o producer que ira ser enviado para o kafka
        ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, jsonString);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        // enviando
        RequestReplyFuture<String, String, String> sendAndReceive = replyingKafkaTemplate.sendAndReceive(record);

        // recebendos o retorno
        SendResult<String, String> sendResult = null;
        try {
            sendResult = sendAndReceive.getSendFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        ConsumerRecord<String, String> consumerRecord = null;
        try {
            consumerRecord = sendAndReceive.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        CustomerDTO customerDTOReturn = null;
        try {
            customerDTOReturn = mapper.readValue(consumerRecord.value(), CustomerDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Return<>(customerDTOReturn);
    }
}
