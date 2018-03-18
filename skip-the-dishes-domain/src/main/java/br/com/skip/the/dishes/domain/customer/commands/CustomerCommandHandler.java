package br.com.skip.the.dishes.domain.customer.commands;

import br.com.skip.the.dishes.domain.customer.Customer;
import br.com.zup.eventsourcing.core.Repository;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;

public class CustomerCommandHandler {

    private final EventStoreRepository<Customer> eventRepository;

    public CustomerCommandHandler(EventStoreRepository<Customer> eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Customer handle(CreateCustomerCommand command) {
        Customer customer = new Customer(command.getName(), command.getEmail(), command.getAddress(), command.getPassword());
        this.eventRepository.save(customer);
        return customer;
    }
}
