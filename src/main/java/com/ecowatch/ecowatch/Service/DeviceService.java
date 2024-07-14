package com.ecowatch.ecowatch.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;
import com.ecowatch.ecowatch.Models.Device.DeviceRepo;
import com.ecowatch.ecowatch.Models.Dto.RegisterDeviceDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterWaterDeviceDto;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;
import com.ecowatch.ecowatch.Models.User.UserRepo;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;
    @Autowired
    private UserRepo userRepo;

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



    // public ResponseEntity<?> addDevice(RegisterDeviceDto newDevice) {
    //     DeviceType type;
    //     if (newDevice instanceof RegisterWaterDeviceDto) ?
    //          DeviceType.Water : DeviceType.Electrical;
        
    //     DeviceEntity existingDevice = deviceRepo.findByDeviceNameAndType(newDevice.getDevice_name(), type);
    //     int quantity = newDevice.getQuantity();
    //     if(quantity > 1) {
    //         List<DeviceEntity> devices = new ArrayList<DeviceEntity>();
    //         for(int i = 1; i < quantity; i++) {
    //             String newDeviceName = newDevice.getDevice_name() + "_" + i;
    //             RegisterDeviceDto deviceCopy = newDevice;
    //             deviceCopy.setDevice_name(newDeviceName);
    //         }
    //     } else {
    //         if(existingDevice != null) {
    //             newDevice.setDevice_name(newDevice.getDevice_name() + "_2");
    //         }
    //     }
    // }

}
