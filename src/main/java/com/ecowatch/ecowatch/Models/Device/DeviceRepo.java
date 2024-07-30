package com.ecowatch.ecowatch.Models.Device;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecowatch.ecowatch.Models.Enums.DeviceType;

@Repository
public interface DeviceRepo extends JpaRepository<DeviceEntity, Long> {
    DeviceEntity findByDeviceNameAndType(String device_name, DeviceType type);
    List<DeviceEntity> findByOrderByInstallationDate();
}
