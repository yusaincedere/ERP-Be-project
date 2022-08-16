package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.BomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomDetailRepository extends JpaRepository<BomDetail,String> {
}
