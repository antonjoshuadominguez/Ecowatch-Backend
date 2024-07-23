package com.ecowatch.ecowatch.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
import com.ecowatch.ecowatch.Models.Consumption.ConsumptionRepo;
import com.ecowatch.ecowatch.Models.Device.DeviceEntity;

@Service
public class ConsumptionService {
    @Autowired
    private ConsumptionRepo consumptionRepo;
    public ConsumptionEntity addConsumption(DeviceEntity device) {
        ConsumptionEntity entity = new ConsumptionEntity(device, 0.0, LocalDateTime.now(ZoneId.of("Asia/Manila")) , null);
        return consumptionRepo.save(entity);
    }
}
