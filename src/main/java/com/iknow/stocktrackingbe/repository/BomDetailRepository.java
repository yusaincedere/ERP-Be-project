package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.bom.BomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BomDetailRepository extends JpaRepository<BomDetail,Long> {

    boolean existsByChildProductId(Long childProductId);


    List<BomDetail> findAllByChildProductId(Long childProductId);

}
