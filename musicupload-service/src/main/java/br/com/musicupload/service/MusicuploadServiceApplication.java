package br.com.musicupload.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {
        "br.com.musicupload.service",
        "br.com.global"
})
@EntityScan(basePackages = {
        "br.com.musicupload.service",
        "br.com.global"
})
@EnableMongoRepositories(value = {
        "br.com.musicupload.service",
        "br.com.global"
})
@EnableKafka
@EnableAsync
public class MusicuploadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicuploadServiceApplication.class, args);
    }

}
