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
    var id: String,

    @NotNull
    @Size(max = PRODUCT_NAME_SIZE)
    var name: String,

    @NotNull
    @Size(max = PRODUCT_DESCRIPTION_SIZE)
    var description: String,

    @NotNull
    @Min(PRODUCT_PRICE_MIN.toLong())
    var price: Double,

    @NotNull
    @Size(max = PRODUCT_STORE_ID_SIZE)
    @Column(name = "store_id")
    var storeId: String
)
