package com.iknow.stocktrackingbe.repository.prospectus;

import com.iknow.stocktrackingbe.model.prospectus.Prospectus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectusRepository extends JpaRepository<Prospectus,Long> {
}
