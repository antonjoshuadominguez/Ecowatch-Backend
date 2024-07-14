package com.ecowatch.ecowatch.Models.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterElectricDeviceDto extends RegisterDeviceDto{
    private double watts;

      public RegisterElectricDeviceDto(String deviceName, long addedBy, int quantity, double watts) {
        super(deviceName, addedBy, quantity);
        this.watts = watts;
    }

    public RegisterElectricDeviceDto(RegisterElectricDeviceDto original) {
        super(original);  
        this.watts = original.watts;
    }
}
