package br.com.customer.service.service;

import br.com.customer.service.model.Customer;
import br.com.customer.service.repository.CustomerRepository;
import br.com.global.Util.Logs;
import br.com.global.Util.Return;
import br.com.global.service.ServiceGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends ServiceGeneric<Customer> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        super(Customer.class);
        this.customerRepository = customerRepository;
    }

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public String listen(String json) {

        ObjectMapper mapper = new ObjectMapper();

        Customer customer = null;
        try {
            customer = mapper.readValue(json, Customer.class);
        } catch (JsonProcessingException e) {
            Logs.error(this.getClass(), e.getMessage());
        }

        Return aReturn = super.save(customerRepository, customer);

        customer.setId(aReturn.getIdSave());

        try {
            return mapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
