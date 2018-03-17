package br.com.skip.the.dishes.domain.product.commons;

import br.com.skip.the.dishes.domain.product.events.DetailUpdated;
import br.com.skip.the.dishes.domain.product.events.PriceUpdated;
import br.com.skip.the.dishes.domain.product.events.ProductCreated;

public interface ProductApplier {

    void apply(ProductCreated productCreated);

    void apply(PriceUpdated priceUpdated);

    void apply(DetailUpdated detailUpdated);

}
