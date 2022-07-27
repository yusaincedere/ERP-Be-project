package com.iknow.stocktrackingbe.repository.production;

import com.iknow.stocktrackingbe.model.production.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface ProductionRepository extends JpaRepository<Production,String> {
    @Transactional
    void deleteByIdIn(List<String> ids);
}
