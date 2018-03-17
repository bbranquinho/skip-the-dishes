package br.com.skip.the.dishes.command.repository;

import br.com.skip.the.dishes.domain.customer.Customer;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepository extends EventStoreRepository<Customer> {
}
