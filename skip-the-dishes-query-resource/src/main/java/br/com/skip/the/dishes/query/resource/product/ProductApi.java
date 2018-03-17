package br.com.skip.the.dishes.query.resource.product;

import br.com.skip.the.dishes.query.repository.product.ProductEntity;
import br.com.skip.the.dishes.query.resource.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = Paths.PRODUCT_PATH)
public interface ProductApi {

    @GetMapping
    ResponseEntity<List<ProductEntity>> findAll(@RequestParam(name = "page", required = false) Integer page,
                                                @RequestParam(name = "size", required = false) Integer size,
                                                @RequestParam(name = "fields", required = false) String fields);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}")
    ResponseEntity<ProductEntity> findById(@PathVariable("id") String id);

}
