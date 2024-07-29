package com.ecowatch.ecowatch.Models.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditWaterDeviceDto {
    private String device_name;
    private Double flow_rate;
}
