package com.ecowatch.ecowatch.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Device.DeviceEntity;
import com.ecowatch.ecowatch.Models.Device.DeviceRepo;
import com.ecowatch.ecowatch.Models.Dto.RegisterElectricDeviceDto;
import com.ecowatch.ecowatch.Models.Electric.ElectricEntity;
import com.ecowatch.ecowatch.Models.Electric.ElectricRepo;
import com.ecowatch.ecowatch.Models.Enums.DeviceType;

@Service
public class ElectricService {
    @Autowired
    private ElectricRepo electricRepo;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRepo deviceRepo;

    public List<ElectricEntity> addElectricDevice(RegisterElectricDeviceDto electricDevice) {
        List<ElectricEntity> devices = new ArrayList<ElectricEntity>();
        int quantity = electricDevice.getQuantity();
        DeviceEntity newDevice = deviceService.addNewDevice(electricDevice);
        if(newDevice != null) { 
            ElectricEntity electricEntity = registerDtoToEntity(electricDevice, newDevice);
            quantity--;
            devices.add(electricEntity);
            electricRepo.save(electricEntity);
        }
        int k = 1;
        for(int i = 1; i <= quantity; i++) {
            RegisterElectricDeviceDto copy = new RegisterElectricDeviceDto(electricDevice);
            while(deviceRepo.findByDeviceNameAndType(copy.getDevice_name(), DeviceType.Electric) != null) {
                copy.setDevice_name(electricDevice.getDevice_name() + "_" + k);
                k++;
            }
            DeviceEntity device = deviceService.registerDtoToEntity(copy);
            ElectricEntity electricEntity = registerDtoToEntity(copy, device);
            devices.add(electricEntity);
            electricRepo.save(electricEntity);
        }
        return devices;
    }

    private ElectricEntity registerDtoToEntity(RegisterElectricDeviceDto dto, DeviceEntity device) {
        return new ElectricEntity(dto.getWatts(), device);
    }
}
