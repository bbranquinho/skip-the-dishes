package br.com.skip.the.dishes.query.resource.order;

import br.com.skip.the.dishes.query.repository.order.OrderEntity;
import br.com.skip.the.dishes.query.repository.order.OrderRepository;
import br.com.skip.the.dishes.query.resource.PaginationUtil;
import br.com.skip.the.dishes.query.resource.Paths;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderResource implements OrderApi {

    private OrderRepository orderRepository;

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 20;

    public OrderResource(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseEntity<List<OrderEntity>> findAll(@RequestParam(name = "page", required = false) Integer page,
                                                     @RequestParam(name = "size", required = false) Integer size,
                                                     @RequestParam(name = "fields", required = false) String fields) {
        if (page == null) {
            page = DEFAULT_PAGE;
        }

        if (size == null) {
            size = DEFAULT_SIZE;
        }

        Page<OrderEntity> orderPage;

        if ((fields != null) && (!fields.trim().isEmpty())) {
            orderPage = orderRepository.findAll(PageRequest.of(page, size, Sort.by(fields.split(","))));
        } else {
            orderPage = orderRepository.findAll(PageRequest.of(page, size));
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(orderPage, Paths.ORDER_PATH);

        return new ResponseEntity(orderPage.getContent(), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderEntity> findById(@PathVariable("id") String id) {
        return orderRepository
                .findById(id)
                .map(p -> new ResponseEntity(p, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

}
