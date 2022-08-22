package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.bom.BomDetail;
import com.iknow.stocktrackingbe.model.product.Product;
import com.iknow.stocktrackingbe.payload.request.bom.BomDetailRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class BomDetailRequestMapper {

    public BomDetail mapToModel(BomDetailRequest bomDetailRequest, Product childProduct, Bom  bom) {
        return new BomDetail().toBuilder()
                .mainProduct(bom.getProduct())
                .quantity(bomDetailRequest.getQuantity())
                .bom(bom)
                .description(bomDetailRequest.getDescription())
                .efficiency(bomDetailRequest.getEfficiency())
                .childProduct(childProduct)
                .build();
    }
}
