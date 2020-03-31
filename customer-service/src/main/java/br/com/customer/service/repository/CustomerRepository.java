package br.com.customer.service.repository;

import br.com.customer.service.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {
}