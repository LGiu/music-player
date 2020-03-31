package br.com.listenermusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {
        "br.com.listenermusic.*",
        "br.com.global"
})
@EntityScan(basePackages = {
        "br.com.listenermusic.*",
        "br.com.global"
})
@EnableMongoRepositories(value = {
        "br.com.listenermusic.*",
        "br.com.global"
})
@EnableAsync
public class ListenerMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ListenerMusicApplication.class, args);
    }

}
