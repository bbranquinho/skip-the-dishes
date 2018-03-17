package br.com.skip.the.dishes.query.resource.order;

import br.com.skip.the.dishes.query.repository.order.OrderEntity;
import br.com.skip.the.dishes.query.resource.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = Paths.ORDER_PATH)
public interface OrderApi {

    @GetMapping
    ResponseEntity<List<OrderEntity>> findAll(@RequestParam(name = "page", required = false) Integer page,
                                              @RequestParam(name = "size", required = false) Integer size,
                                              @RequestParam(name = "fields", required = false) String fields);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}")
    ResponseEntity<OrderEntity> findById(@PathVariable("id") String id);

}
