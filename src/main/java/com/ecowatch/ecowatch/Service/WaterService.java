package com.ecowatch.ecowatch.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;
import com.ecowatch.ecowatch.Models.Device.DeviceRepo;
import com.ecowatch.ecowatch.Models.Dto.EditWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.Water.WaterEntity;
import com.ecowatch.ecowatch.Models.Water.WaterRepo;

@Service
public class WaterService {
    @Autowired
    private WaterRepo waterRepo;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRepo deviceRepo;

    public List<WaterEntity> addWaterDevice(RegisterWaterDeviceDto waterDevice) {
        List<WaterEntity> devices = new ArrayList<WaterEntity>();
        int quantity = waterDevice.getQuantity();
        DeviceEntity newDevice = deviceService.addNewDevice(waterDevice);
        if(newDevice != null) { 
            WaterEntity waterEntity = registerDtoToEntity(waterDevice, newDevice);
            quantity--;
            devices.add(waterEntity);
            waterRepo.save(waterEntity);
        }
        int k = 1;
        for(int i = 1; i <= quantity; i++) {
            RegisterWaterDeviceDto copy = new RegisterWaterDeviceDto(waterDevice);
            while(deviceRepo.findByDeviceNameAndType(copy.getDevice_name(), DeviceType.Water) != null) {
                copy.setDevice_name(waterDevice.getDevice_name() + "_" + k);
                k++;
            }
            DeviceEntity device = deviceService.registerDtoToEntity(copy);
            WaterEntity waterEntity = registerDtoToEntity(copy, device);
            devices.add(waterEntity);
            waterRepo.save(waterEntity);
        }
        return devices;
    }

    public ResponseEntity<?> editWaterDevice(long deviceId, EditWaterDeviceDto waterDevice) {
        if(!deviceRepo.existsById(deviceId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid device ID. No device record is found.");
        }
        DeviceEntity device = deviceRepo.findById(deviceId).get();
        if(deviceRepo.findByDeviceNameAndType(waterDevice.getDevice_name(), DeviceType.Water) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Device Name Edit Conflict. There is an existing device with this name.");
        }
        if(waterDevice.getDevice_name()!= null) {
            device.setDeviceName(waterDevice.getDevice_name());
        }
        WaterEntity wDevice = waterRepo.findById(deviceId).get();
        if(waterDevice.getFlow_rate() != null) {
            wDevice.setFlow_rate(waterDevice.getFlow_rate());
        }
        deviceRepo.save(device);
        waterRepo.save(wDevice);
        return ResponseEntity.ok(wDevice);
    }

    private WaterEntity registerDtoToEntity(RegisterWaterDeviceDto dto, DeviceEntity device) {
        return new WaterEntity(dto.getFlow_rate(), device);
    }
}
