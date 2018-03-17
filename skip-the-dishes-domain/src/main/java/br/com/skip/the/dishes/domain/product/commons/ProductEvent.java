package br.com.skip.the.dishes.domain.product.commons;

public interface ProductEvent {

    void accept(ProductApplier productApplier);

}
