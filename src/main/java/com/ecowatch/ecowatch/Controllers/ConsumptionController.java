package com.ecowatch.ecowatch.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Service.ConsumptionService;

import io.swagger.v3.oas.annotations.*;

@RestController
@CrossOrigin
@RequestMapping(path="/api/consumption")
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;

    @Operation(summary = "Get all consumption history")
    @GetMapping
    public ResponseEntity<List<ConsumptionEntity>> getConsumptionHistory() {
        return ResponseEntity.ok(consumptionService.getAllConsumption());
    }
   
    @Operation(summary = "Get device consumption history")
    @GetMapping("/{deviceId}")
    public ResponseEntity<List<ConsumptionEntity>> getDeviceConsumptionHistory(@RequestParam long deviceId) {
        return ResponseEntity.ok(consumptionService.getConsumptionByDevice(deviceId));
    }

    @Operation(summary = "Get total consumption by type")
    @GetMapping("/total/{type}")
    public ResponseEntity<Double> getTotalConsumption(@RequestParam(required = true) DeviceType type) {
        return ResponseEntity.ok(consumptionService.getTotalConsumption(type));
    }
}
