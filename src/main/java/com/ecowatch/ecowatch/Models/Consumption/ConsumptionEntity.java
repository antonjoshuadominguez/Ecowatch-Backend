package com.ecowatch.ecowatch.Models.Consumption;

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
    private int usage_frequency;
    private double cost;

    @ManyToOne
    @JoinColumn(name = "device", nullable = false)
    private DeviceEntity device;
}
