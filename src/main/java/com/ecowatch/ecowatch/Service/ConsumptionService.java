package com.ecowatch.ecowatch.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
import com.ecowatch.ecowatch.Models.Consumption.ConsumptionRepo;
import com.ecowatch.ecowatch.Models.Device.DeviceEntity;
import com.ecowatch.ecowatch.Models.Device.DeviceRepo;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;

@Service
public class ConsumptionService {
    @Autowired
    private ConsumptionRepo consumptionRepo;
    @Autowired
    private DeviceRepo deviceRepo;
    public ConsumptionEntity addConsumption(DeviceEntity device) {
        ConsumptionEntity entity = new ConsumptionEntity(device,0.0, 0.0, LocalDateTime.now(ZoneId.of("Asia/Manila")) , null);
        return consumptionRepo.save(entity);
    }

    public List<ConsumptionEntity> getAllConsumption() {
        return consumptionRepo.findAll();
    }

    public List<ConsumptionEntity> getConsumptionByDevice(long deviceId) {
        DeviceEntity device = deviceRepo.findById(deviceId).get();
        return device.getConsumption_history(); 
    }

    public double getTotalConsumption(DeviceType type) {
        List<ConsumptionEntity> consumptionList = consumptionRepo.findByDevice_Type(type);
        double total = 0.0;
        for (ConsumptionEntity consumptionEntity : consumptionList) {
            total += consumptionEntity.getUsage();
        }
        return total;
    }
}
