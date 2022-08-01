package com.iknow.stocktrackingbe.repository.prescription;

import com.iknow.stocktrackingbe.model.prescription.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface PrescriptionProductRepository extends JpaRepository<PrescriptionProduct,String> {
    @Transactional
    void deleteByIdIn(ArrayList<String> ids);

    List<PrescriptionProduct> findAllByProductIn(List<Product> products);

}
