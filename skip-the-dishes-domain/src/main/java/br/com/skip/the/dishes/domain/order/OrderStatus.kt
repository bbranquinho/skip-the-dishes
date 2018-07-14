package br.com.skip.the.dishes.domain.order

enum class OrderStatus constructor(private val status: String) {

    CANCELLED("Cancelled"),

    FINISHED("Finished"),

    NEW("New");

    private companion object {
        val MAP_STATUS: Map<String, OrderStatus> = values().associateBy { it.status }
    }

    override fun toString(): String =
        status

    fun parse(status: String): OrderStatus? =
            MAP_STATUS[status.toUpperCase()]

}
