package br.com.skip.the.dishes.query.repository.product

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import br.com.skip.the.dishes.domain.utils.DomainConstants.*

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @NotNull
    @Size(max = PRODUCT_ID_SIZE)
    val id: String,

    @NotNull
    @Size(max = PRODUCT_NAME_SIZE)
    val name: String,

    @NotNull
    @Size(max = PRODUCT_DESCRIPTION_SIZE)
    val description: String,

    @NotNull
    @Min(PRODUCT_PRICE_MIN.toLong())
    val price: Double,

    @NotNull
    @Size(max = PRODUCT_STORE_ID_SIZE)
    @Column(name = "store_id")
    val storeId: String
)
