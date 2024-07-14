package com.ecowatch.ecowatch.Models.Consumption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepo extends JpaRepository<ConsumptionEntity, Long> {
    
}
