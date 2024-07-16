package com.ecowatch.ecowatch.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;
import com.ecowatch.ecowatch.Models.Device.DeviceRepo;
import com.ecowatch.ecowatch.Models.Dto.RegisterDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Electric.ElectricEntity;
import com.ecowatch.ecowatch.Models.Electric.ElectricRepo;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.User.UserRepo;
import com.ecowatch.ecowatch.Models.Water.WaterEntity;
import com.ecowatch.ecowatch.Models.Water.WaterRepo;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WaterRepo waterRepo;
    @Autowired
    private ElectricRepo electricRepo;

    public DeviceEntity addNewDevice(RegisterDeviceDto newDevice) {
        DeviceType type = (newDevice instanceof RegisterWaterDeviceDto) ? DeviceType.Water : DeviceType.Electrical;
        if(deviceRepo.findByDeviceNameAndType(newDevice.getDevice_name(), type) == null) {
            deviceRepo.save(registerDtoToEntity(newDevice));
            return deviceRepo.findByDeviceNameAndType(newDevice.getDevice_name(), type);
        }
        return null;
    }

    public DeviceEntity registerDtoToEntity(RegisterDeviceDto dto) {
        DeviceType type = (dto instanceof RegisterWaterDeviceDto) ? DeviceType.Water : DeviceType.Electrical;
        return new DeviceEntity(
            dto.getDevice_name(), 
            type, 
            Date.from(LocalDate.now().atStartOfDay(ZoneId.of("Asia/Manila")).toInstant()), 
            false,
            userRepo.findById(dto.getAdded_by()).get()
            );
    }

    public ResponseEntity<?> getAllDevices(DeviceType type) {
        List<Object> response = new ArrayList<>();
        boolean typeIsNull = (type == null) ? true : false;
        if(typeIsNull || type.equals(DeviceType.Water)) {
            List<WaterEntity> waterDevices = waterRepo.findAll();
            response.addAll(waterDevices);
        }
        if(typeIsNull ||type.equals(DeviceType.Electrical)) {
            List<ElectricEntity> electricDevices = electricRepo.findAll();
            response.addAll(electricDevices);
        }
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No device found");
        }
        return ResponseEntity.ok(response);
    }

}
