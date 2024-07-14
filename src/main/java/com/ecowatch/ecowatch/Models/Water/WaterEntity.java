package com.ecowatch.ecowatch.Models.Water;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "water_usage", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WaterEntity {
    @Id
    private long id;
    private double flow_rate;
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private DeviceEntity device;
    public WaterEntity(double flow_rate, DeviceEntity device) {
        this.flow_rate = flow_rate;
        this.device = device;
    }
    
}
