package com.ecowatch.ecowatch.Models.Electric;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "electric_usage", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ElectricEntity {
    @Id
    private long id;
    private double watts;
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private DeviceEntity device;
    public ElectricEntity(double watts, DeviceEntity device) {
        this.watts = watts;
        this.device = device;
    }
    
}
