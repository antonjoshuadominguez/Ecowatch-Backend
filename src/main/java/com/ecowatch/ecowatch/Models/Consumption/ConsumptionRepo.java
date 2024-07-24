package com.ecowatch.ecowatch.Models.Consumption;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecowatch.ecowatch.Models.Enums.DeviceType;

@Repository
public interface ConsumptionRepo extends JpaRepository<ConsumptionEntity, Long> {
    List<ConsumptionEntity> findByDevice_Type(DeviceType type);
}
