package br.com.skip.the.dishes.query.repository.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, String>
