package com.ecowatch.ecowatch.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Dto.RegisterElectricDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Electric.ElectricEntity;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.Water.WaterEntity;
import com.ecowatch.ecowatch.Service.DeviceService;
import com.ecowatch.ecowatch.Service.ElectricService;
import com.ecowatch.ecowatch.Service.WaterService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("api/device")
public class DeviceController {
    @Autowired
    private WaterService waterService;
    @Autowired
    private ElectricService electricService;
    @Autowired
    private DeviceService deviceService;

    @Operation(summary = "Add a water device")
    @PostMapping("/water")
    public ResponseEntity<List<WaterEntity>> addWaterDevice(@RequestBody RegisterWaterDeviceDto newDevice) {
        return ResponseEntity.ok(waterService.addWaterDevice(newDevice));
    }

    @Operation(summary = "Add an electric device")
    @PostMapping("/electric")
    public ResponseEntity<List<ElectricEntity>> addElectricDevice(@RequestBody RegisterElectricDeviceDto newDevice) {
        return ResponseEntity.ok(electricService.addElectricDevice(newDevice));
    }

    @Operation(summary = "Get all devices")
    @GetMapping()
    public ResponseEntity<?> getAllDevices(@RequestParam(required = false) DeviceType type) {
        return deviceService.getAllDevices(type);
    }
}
