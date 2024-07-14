package com.ecowatch.ecowatch.Models.Cost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepo extends JpaRepository<CostEntity, Long> {
    
}
