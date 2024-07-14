package com.ecowatch.ecowatch.Models.SavingTips;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingTipRepo extends JpaRepository<SavingTipEntity, Long>{
    
}
