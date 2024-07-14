package com.ecowatch.ecowatch.Models.Electric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricRepo extends JpaRepository<ElectricEntity, Long> {
    
}
