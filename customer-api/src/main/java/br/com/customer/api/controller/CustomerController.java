package br.com.customer.api.controller;


import br.com.customer.api.service.CustomerService;
import br.com.customer.api.DTO.CustomerDTO;
import br.com.global.Util.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Value("${kafka.topic.request-topic}")
    String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    String requestReplyTopic;

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Return> saveCustumer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.execute(customerDTO, requestTopic, requestReplyTopic), HttpStatus.OK);

    }

}