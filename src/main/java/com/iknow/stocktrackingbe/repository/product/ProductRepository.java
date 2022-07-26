package com.iknow.stocktrackingbe.repository.product;

import com.iknow.stocktrackingbe.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
