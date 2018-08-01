package br.com.skip.the.dishes.query.resource.product

import br.com.skip.the.dishes.query.repository.product.ProductEntity
import br.com.skip.the.dishes.query.resource.Paths
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping(path = [(Paths.PRODUCT_PATH)])
interface ProductApi {

    companion object {
        const val DEFAULT_PAGE = 0

        const val DEFAULT_SIZE = 20
    }

    @GetMapping
    fun findAll(@RequestParam(name = "page", defaultValue = DEFAULT_PAGE.toString()) page: Int,
                @RequestParam(name = "size", defaultValue = DEFAULT_SIZE.toString()) size: Int,
                @RequestParam(name = "fields", defaultValue = "") fields: String): ResponseEntity<List<ProductEntity>>

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = ["/{id}"])
    fun findById(@PathVariable("id") id: String): ResponseEntity<ProductEntity>

}
