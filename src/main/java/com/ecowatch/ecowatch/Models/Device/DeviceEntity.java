package com.ecowatch.ecowatch.Models.Device;

import java.util.*;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.User.UserEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "devices", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deviceId;
    private String deviceName;
    private DeviceType type;
    private Date installationDate;
    private boolean isDeviceOn;

    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private UserEntity added_by;

    @OneToMany(mappedBy = "device")
    private List<ConsumptionEntity> consumption_history;

    public DeviceEntity(String device_name, DeviceType type, Date installation_date, boolean isDeviceOn,
            UserEntity added_by) {
        this.deviceName = device_name;
        this.type = type;
        this.installationDate = installation_date;
        this.isDeviceOn = isDeviceOn;
        this.added_by = added_by;
    }
    
}
