package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductionRepository extends JpaRepository<Production,String> {

    void deleteByIdIn(List<String> ids);
}
