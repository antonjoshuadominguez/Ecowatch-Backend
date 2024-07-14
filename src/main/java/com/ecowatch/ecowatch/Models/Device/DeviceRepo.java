package com.ecowatch.ecowatch.Models.Device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<DeviceEntity, Long> {
    
}
