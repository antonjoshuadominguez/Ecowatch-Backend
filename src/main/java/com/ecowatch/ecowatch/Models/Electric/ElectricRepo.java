package com.ecowatch.ecowatch.Models.Electric;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricRepo extends JpaRepository<ElectricEntity, Long> {
    List<ElectricEntity> findByOrderByDevice_InstallationDate();
    List<ElectricEntity> findByOrderByDevice_DeviceName();
}
