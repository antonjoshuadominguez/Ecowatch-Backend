package com.ecowatch.ecowatch.Models.Device;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "devices", schema = "public")
public class DeviceEntity {
    @Id
    private long device_id;
    
}
