package br.com.skip.the.dishes.query.resource.product

import br.com.skip.the.dishes.query.repository.product.ProductEntity
import br.com.skip.the.dishes.query.repository.product.ProductRepository
import br.com.skip.the.dishes.query.resource.PaginationUtil
import br.com.skip.the.dishes.query.resource.Paths
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductResource(private val productRepository: ProductRepository) : ProductApi {

    override fun findAll(@RequestParam(name = "page", defaultValue = ProductApi.DEFAULT_PAGE.toString()) page: Int,
                         @RequestParam(name = "size", defaultValue = ProductApi.DEFAULT_SIZE.toString()) size: Int,
                         @RequestParam(name = "fields", defaultValue = "") fields: String): ResponseEntity<List<ProductEntity>> {

        val productPage: Page<ProductEntity> = if (!fields.trim { it <= ' ' }.isEmpty()) {
            productRepository.findAll(PageRequest.of(page, size, Sort.by(*fields.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())))
        } else {
            productRepository.findAll(PageRequest.of(page, size))
        }

        val headers = PaginationUtil.generatePaginationHttpHeaders(productPage, Paths.PRODUCT_PATH)

        return ResponseEntity(productPage.content, headers, HttpStatus.OK)
    }

    override fun findById(@PathVariable("id") id: String): ResponseEntity<ProductEntity> =
            productRepository
                    .findById(id)
                    .map<ResponseEntity<ProductEntity>> { p -> ResponseEntity(p, HttpStatus.OK) }
                    .orElse(ResponseEntity(HttpStatus.NOT_FOUND))

}
