package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Prospectus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProspectusRepository extends JpaRepository<Prospectus,String> {
}
