package com.iknow.stocktrackingbe.repository.product;
import com.iknow.stocktrackingbe.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    @Transactional
    void deleteByIdIn(List<String> ids);
}
