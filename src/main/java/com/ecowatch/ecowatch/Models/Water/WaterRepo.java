package com.ecowatch.ecowatch.Models.Water;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRepo extends JpaRepository<WaterEntity, Long> {
    
}
