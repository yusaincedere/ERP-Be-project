package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.bom.Bom;
import com.iknow.stocktrackingbe.model.bom.BomDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface BomRepository  extends JpaRepository<Bom,Long > {


    boolean existsByBomCode(String code);
    boolean existsByBomName(String name);


    Page<Bom> findAllByBomName(String name, Pageable pageable);

    void deleteByIdIn(Set<Long> ids);

    List<Bom> findAllByIdIn(List<Long> ids);

    List<Bom> findAllByProductId(Long productId);



}
