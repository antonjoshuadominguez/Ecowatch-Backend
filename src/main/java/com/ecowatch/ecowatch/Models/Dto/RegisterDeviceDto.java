package com.ecowatch.ecowatch.Models.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDeviceDto {
    private String device_name;
    private long added_by;
    private int quantity;

    public RegisterDeviceDto(RegisterDeviceDto original) {
        this.device_name = original.device_name;
        this.added_by = original.added_by;
        this.quantity = original.quantity;
    }
}
