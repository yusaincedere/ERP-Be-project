package com.iknow.stocktrackingbe.payload.request.mapper;
import com.iknow.stocktrackingbe.model.Bom;
import com.iknow.stocktrackingbe.model.BomDetail;
import com.iknow.stocktrackingbe.model.Product;
import com.iknow.stocktrackingbe.payload.request.BomDetailRequest;
import org.springframework.stereotype.Component;


@Component
public class BomDetailRequestMapper {

    public BomDetail mapToModel(BomDetailRequest bomDetailRequest, Product mainProduct, Product childProduct,Bom bom) {
        return new BomDetail().toBuilder()
                .mainProduct(mainProduct)
                .childAmount(bomDetailRequest.getChildAmount())
                .mainAmount(bomDetailRequest.getMainAmount())
                .childProduct(childProduct)
                .bom(bom)
                .build();
    }
}
