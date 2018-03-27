package br.com.skip.the.dishes.command.resource.product;

import br.com.skip.the.dishes.domain.product.Detail;
import br.com.skip.the.dishes.domain.product.Product;
import br.com.skip.the.dishes.domain.product.commands.CreateProductCommand;
import br.com.skip.the.dishes.domain.product.commands.ProductCommandHandler;
import br.com.skip.the.dishes.domain.product.commands.UpdateDetailCommand;
import br.com.skip.the.dishes.domain.product.commands.UpdatePriceCommand;
import br.com.zup.eventsourcing.core.AggregateId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_ID_SIZE;

@RestController
public class ProductResource implements ProductApi {

    private ProductCommandHandler productCommandHandler;

    public ProductResource(ProductCommandHandler productCommandHandler) {
        this.productCommandHandler = productCommandHandler;
    }

    @Override
    public CreateResponse create(@RequestBody @Valid CreateRequest createRequest) {
        CreateProductCommand command = new CreateProductCommand(createRequest.getName(), createRequest.getDescription(),
                createRequest.getStoreId(), createRequest.getPrice());
        Product product = productCommandHandler.handle(command);
        return new CreateResponse(product.getProductId());
    }

    @Override
    public void updateDetail(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) String id,
                             @RequestBody @Valid DetailRequest detailRequest) {
        AggregateId productId = new AggregateId(id);
        UpdateDetailCommand command = new UpdateDetailCommand(productId, new Detail(detailRequest.getName(),
                detailRequest.getDescription()));
        productCommandHandler.handle(command);
    }

    @Override
    public void updatePrice(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) String id,
                            @RequestBody @Valid PriceRequest priceRequest) {
        AggregateId productId = new AggregateId(id);
        UpdatePriceCommand command = new UpdatePriceCommand(productId, priceRequest.getPrice());
        productCommandHandler.handle(command);
    }

}
