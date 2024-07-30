package com.ecowatch.ecowatch.Models.Water;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRepo extends JpaRepository<WaterEntity, Long> {
    List<WaterEntity> findByOrderByDevice_InstallationDate();
    List<WaterEntity> findByOrderByDevice_DeviceName();
}
