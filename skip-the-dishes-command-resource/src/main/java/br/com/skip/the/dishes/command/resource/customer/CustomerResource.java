package br.com.skip.the.dishes.command.resource.customer;

import br.com.skip.the.dishes.domain.customer.Customer;
import br.com.skip.the.dishes.domain.customer.commands.CreateCustomerCommand;
import br.com.skip.the.dishes.domain.customer.commands.CustomerCommandHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerResource implements CustomerApi {

    private final CustomerCommandHandler customerCommandHandler;

    public CustomerResource(CustomerCommandHandler customerCommandHandler) {
        this.customerCommandHandler = customerCommandHandler;
    }

    @Override
    public CreateResponse create(@RequestBody @Valid CreateRequest createRequest) {
        CreateCustomerCommand command = new CreateCustomerCommand(createRequest.getName(), createRequest.getEmail(),
                createRequest.getAddress(), createRequest.getPassword());
        Customer customer = customerCommandHandler.handle(command);
        return new CreateResponse(customer.getCustomerId());
    }

}
