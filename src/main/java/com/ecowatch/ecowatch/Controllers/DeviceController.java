package com.ecowatch.ecowatch.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Dto.EditElectricDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.EditWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterElectricDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Electric.ElectricEntity;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.Water.WaterEntity;
import com.ecowatch.ecowatch.Service.DeviceService;
import com.ecowatch.ecowatch.Service.ElectricService;
import com.ecowatch.ecowatch.Service.WaterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    @Operation(summary = "Edit a water device")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = WaterEntity.class)))
    @PutMapping("/water/{deviceId}")
    public ResponseEntity<?> editWaterDevice(@RequestParam long deviceId, @RequestBody EditWaterDeviceDto waterDevice) {
        return ResponseEntity.ok(waterService.editWaterDevice(deviceId, waterDevice));
    }

    @Operation(summary = "Edit an electric device")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ElectricEntity.class)))
    @PutMapping("/electric/{deviceId}")
    public ResponseEntity<?> editElectricDevice(@RequestParam long deviceId, @RequestBody EditElectricDeviceDto elecctricDevice) {
        return ResponseEntity.ok(electricService.editElectricDevice(deviceId, elecctricDevice));
    }

    @Operation(summary = "Get all devices")
    @GetMapping("/all")
    public ResponseEntity<?> getAllDevices(@RequestParam(required = false) DeviceType type) {
        return deviceService.getAllDevices(type);
    }

    @Operation(summary = "Get a device by device ID")
    @GetMapping("/{deviceId}")
    public ResponseEntity<?> getDevice(@RequestParam(required = true) Long deviceId) {
        return deviceService.getDevice(deviceId);
    }

    @Operation(summary = "Turn on a device by device ID")
    @PostMapping("/{deviceId}/on")
    public ResponseEntity<?> turnOnDevice(@RequestParam(required = true) Long deviceId) {
        return deviceService.turnOnDevice(deviceId);
    }

    @Operation(summary = "Turn off a device by device ID")
    @PatchMapping("/{deviceId}/off")
    public ResponseEntity<?> turnOffDevice(@RequestParam(required = true) Long deviceId) {
        return deviceService.turnOffDevice(deviceId);
    }

    @Operation(summary = "Delete a device permanently")
    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<String> deleteDevice(@RequestParam(required = true) Long deviceId) {
        return deviceService.deleteDevice(deviceId);
    }
}
