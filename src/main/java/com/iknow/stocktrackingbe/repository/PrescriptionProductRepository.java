package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.PrescriptionProduct;
import com.iknow.stocktrackingbe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
@Transactional
public interface PrescriptionProductRepository extends JpaRepository<PrescriptionProduct,String> {
    void deleteByIdIn(Set<String> ids);

    List<PrescriptionProduct> findAllByProductIn(List<Product> products);

}
