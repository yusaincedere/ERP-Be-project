package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.product.Dimension;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.model.product.Weight;
import com.iknow.stocktrackingbe.payload.request.product.DimensionRequest;
import com.iknow.stocktrackingbe.payload.request.product.ProductRequest;
import com.iknow.stocktrackingbe.payload.request.product.WeightRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {

    public Product mapToModel(ProductRequest productRequest) {
        return new Product().toBuilder()
                .productName(productRequest.getProductName())
                .productCode(productRequest.getProductCode())
                .productType(productRequest.getProductType())
                .productUnit(productRequest.getProductUnit())
                .cost(productRequest.getCost())
                .description(productRequest.getDescription())
                .dimension(mapToDimension(productRequest.getDimension()))
                .selPrice(productRequest.getSelPrice())
                .toBuy(productRequest.getToBuy())
                .toSell(productRequest.getToSell())
                .url(productRequest.getUrl())
                .weight(mapToWeight(productRequest.getWeight()))
                .build();
    }


    public Dimension mapToDimension(DimensionRequest dimensionRequest){
        return new Dimension().toBuilder()
                .dimensionType(dimensionRequest.getDimensionType())
                .height(dimensionRequest.getHeight())
                .length(dimensionRequest.getLength())
                .width(dimensionRequest.getWidth())
                .build();
    }

    public Weight mapToWeight(WeightRequest weightRequest){
        return new Weight().toBuilder()
                .weightType(weightRequest.getWeightType())
                .amount(weightRequest.getAmount())
                .build();
    }
}
