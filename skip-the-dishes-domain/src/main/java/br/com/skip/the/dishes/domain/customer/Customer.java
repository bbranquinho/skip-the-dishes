package br.com.skip.the.dishes.domain.customer;

import br.com.skip.the.dishes.domain.customer.commons.CustomerApplier;
import br.com.skip.the.dishes.domain.customer.commons.CustomerEvent;
import br.com.skip.the.dishes.domain.customer.events.CustomerCreated;
import br.com.skip.the.dishes.domain.order.commons.OrderEvent;
import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.core.AggregateRoot;
import br.com.zup.eventsourcing.core.Event;

import java.util.UUID;

public class Customer extends AggregateRoot {

    private final CustomerApplier applier = new Applier();

    private String name;

    private String email;

    private String address;

    private String password;

    public Customer() {
    }

    public Customer(String name, String email, String address, String password) {
        String customerId = UUID.randomUUID().toString();
        CustomerCreated customerCreatedEvent = new CustomerCreated(customerId, name, email, address, password);
        applyChange(customerCreatedEvent);
    }

    @Override
    public void applyEvent(Event event) {
        ((CustomerEvent)event).accept(id, applier);
    }

    private class Applier implements CustomerApplier {

        @Override
        public void on(AggregateId aggregateId, CustomerCreated customerCreated) {
            id = new AggregateId(customerCreated.getCustomerId());
            name = customerCreated.getName();
            email = customerCreated.getEmail();
            address = customerCreated.getAddress();
            password = customerCreated.getPassword();
        }

    }

    public String getCustomerId() {
        return id.getValue();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
