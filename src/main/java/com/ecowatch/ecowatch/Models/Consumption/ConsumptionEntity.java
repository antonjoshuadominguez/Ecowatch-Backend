package com.ecowatch.ecowatch.Models.Consumption;

import java.time.LocalDateTime;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consumption", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsumptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "device", nullable = false)
    private DeviceEntity device;

    private double usage;
    private double usageInHrs;
    private LocalDateTime deviceIsOn;
    private LocalDateTime deviceIsOff;
    public ConsumptionEntity(DeviceEntity device, double usage, double usageInHrs, LocalDateTime deviceIsOn,
            LocalDateTime deviceIsOff) {
        this.device = device;
        this.usage = usage;
        this.usageInHrs = usageInHrs;
        this.deviceIsOn = deviceIsOn;
        this.deviceIsOff = deviceIsOff;
    }
    
    
    
}
