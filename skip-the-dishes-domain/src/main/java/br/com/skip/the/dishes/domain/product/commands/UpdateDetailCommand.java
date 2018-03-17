package br.com.skip.the.dishes.domain.product.commands;

import br.com.skip.the.dishes.domain.product.Detail;
import br.com.zup.eventsourcing.core.AggregateId;

public class UpdateDetailCommand {

    private AggregateId productId;

    private Detail detail;

    public UpdateDetailCommand(AggregateId productId, Detail detail) {
        this.productId = productId;
        this.detail = detail;
    }

    public AggregateId getProductId() {
        return productId;
    }

    public void setProductId(AggregateId productId) {
        this.productId = productId;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
