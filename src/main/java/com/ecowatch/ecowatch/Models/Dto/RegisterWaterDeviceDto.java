package com.ecowatch.ecowatch.Models.Dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterWaterDeviceDto extends RegisterDeviceDto{
    private double flow_rate;

    public RegisterWaterDeviceDto(String deviceName, long addedBy, int quantity, double flowRate) {
        super(deviceName, addedBy, quantity);
        this.flow_rate = flowRate;
    }

    public RegisterWaterDeviceDto(RegisterWaterDeviceDto original) {
        super(original);  
        this.flow_rate = original.flow_rate;
    }
}
