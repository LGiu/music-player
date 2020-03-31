package br.com.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {
        "br.com.customer.service",
        "br.com.global"
})
@EntityScan(basePackages = {
        "br.com.customer.service",
        "br.com.global"
})
@EnableMongoRepositories(value = {
        "br.com.customer.service",
        "br.com.global"
})
@EnableKafka
@EnableAsync
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
