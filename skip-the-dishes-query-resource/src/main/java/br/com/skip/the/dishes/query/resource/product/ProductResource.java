package br.com.skip.the.dishes.query.resource.product;

import br.com.skip.the.dishes.query.repository.product.ProductEntity;
import br.com.skip.the.dishes.query.repository.product.ProductRepository;
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
public class ProductResource implements ProductApi {

    private ProductRepository productRepository;

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 20;

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductEntity>> findAll(@RequestParam(name = "page", required = false) Integer page,
                                                       @RequestParam(name = "size", required = false) Integer size,
                                                       @RequestParam(name = "sortFields", required = false) String sortFields) {
        if (page == null) {
            page = DEFAULT_PAGE;
        }

        if (size == null) {
            size = DEFAULT_SIZE;
        }

        Page<ProductEntity> productPage;

        if ((sortFields != null) && (!sortFields.trim().isEmpty())) {
            productPage = productRepository.findAll(PageRequest.of(page, size, Sort.by(sortFields.split(","))));
        } else {
            productPage = productRepository.findAll(PageRequest.of(page, size));
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(productPage, Paths.PRODUCT_PATH);

        return new ResponseEntity(productPage.getContent(), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductEntity> findById(@PathVariable("id") String id) {
        return productRepository
                .findById(id)
                .map(p -> new ResponseEntity(p, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
