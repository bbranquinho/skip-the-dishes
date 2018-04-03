package br.com.skip.the.dishes.security.application.commons

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.jpa.domain.AbstractPersistable
import java.io.Serializable

abstract class BaseEntity<ID : Serializable> : AbstractPersistable<ID>() {

    public override fun setId(id: ID) {
        super.setId(id)
    }

    @JsonIgnore
    override fun isNew(): Boolean =
            super.isNew()

}
