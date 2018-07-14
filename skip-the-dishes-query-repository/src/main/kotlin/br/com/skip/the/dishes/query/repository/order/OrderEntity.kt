package br.com.skip.the.dishes.query.repository.order

import br.com.skip.the.dishes.query.repository.product.ProductEntity
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import br.com.skip.the.dishes.domain.utils.DomainConstants.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "orders")
data class OrderEntity(
        @Id
        @Size(max = ORDER_ID_SIZE)
        val id: String,

        @Size(max = CUSTOMER_ID_SIZE)
        @Column(name = "customer_id")
        val customerId: String,

        @Size(max = ORDER_DELIVERY_ADDRESS_SIZE)
        @Column(name = "delivery_address")
        val deliveryAddress: String? = null,

        @NotNull
        @Size(max = ORDER_CONTACT_SIZE)
        val contact: String? = null,

        @NotNull
        val status: String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "order_item",
                joinColumns = [JoinColumn(name = "order_id")],
                inverseJoinColumns = [JoinColumn(name = "product_id")]
        )
        val products: List<ProductEntity> = listOf()
)
