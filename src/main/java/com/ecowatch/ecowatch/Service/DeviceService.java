package com.ecowatch.ecowatch.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
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
    @Autowired
    private ConsumptionService consumptionService;

    public DeviceEntity addNewDevice(RegisterDeviceDto newDevice) {
        DeviceType type = (newDevice instanceof RegisterWaterDeviceDto) ? DeviceType.Water : DeviceType.Electric;
        if(deviceRepo.findByDeviceNameAndType(newDevice.getDevice_name(), type) == null) {
            deviceRepo.save(registerDtoToEntity(newDevice));
            return deviceRepo.findByDeviceNameAndType(newDevice.getDevice_name(), type);
        }
        return null;
    }

    public DeviceEntity registerDtoToEntity(RegisterDeviceDto dto) {
        DeviceType type = (dto instanceof RegisterWaterDeviceDto) ? DeviceType.Water : DeviceType.Electric;
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
            List<WaterEntity> waterDevices = waterRepo.findByOrderByDevice_DeviceName();
            response.addAll(waterDevices);
        }
        if(typeIsNull || type.equals(DeviceType.Electric)) {
            List<ElectricEntity> electricDevices = electricRepo.findByOrderByDevice_DeviceName();
            response.addAll(electricDevices);
        }
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No device found");
        }
        Collections.sort(response, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                String name1 = getDeviceName(o1);
                String name2 = getDeviceName(o2);
                return name1.compareToIgnoreCase(name2);
            }
        });
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getDevice(Long deviceId) {
        DeviceEntity deviceEntity = (deviceRepo.existsById(deviceId)) ? deviceRepo.findById(deviceId).get() : null;
        if(deviceEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid device ID. No device found");
        } 
        DeviceType type = deviceEntity.getType();
        return (type.equals(DeviceType.Water)) ? ResponseEntity.ok(waterRepo.findById(deviceId)) 
            : ResponseEntity.ok(electricRepo.findById(deviceId));       
    }

    public ResponseEntity<?> turnOnDevice(long deviceId) {
        if(!deviceRepo.existsById(deviceId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid device ID. No device found");
        }
        DeviceEntity device = deviceRepo.findById(deviceId).get();
        if(device.isDeviceOn()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Device is already turned on.");
        }
        ConsumptionEntity consumption = consumptionService.addConsumption(device);
        device.setDeviceOn(true);
        deviceRepo.save(device);
        return ResponseEntity.ok(consumption);
    }

    public ResponseEntity<?> turnOffDevice(long deviceId) {
        if(!deviceRepo.existsById(deviceId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid device ID. No device found");
        }
        DeviceEntity device = deviceRepo.findById(deviceId).get();
        double unit = 0.0;
        if(device.getType().equals(DeviceType.Electric)) {
            ElectricEntity electric = electricRepo.findById(device.getDeviceId()).get();
            unit = electric.getWatts();
        } else {
            WaterEntity water = waterRepo.findById(device.getDeviceId()).get();
            unit = water.getFlow_rate();
        }
        ConsumptionEntity consumption = null;
        List<ConsumptionEntity> consumptionHistory = device.getConsumption_history();
        for (ConsumptionEntity consumptionEntity : consumptionHistory) {
            if(consumptionEntity.getDeviceIsOff()==null) {
                consumption = consumptionEntity;
                break;
            }
        }
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Manila"));
        consumption.setDeviceIsOff(now);
        Duration duration = Duration.between(consumption.getDeviceIsOn(), now);
        double hours = duration.toSeconds() / 3600.0;
        consumption.setUsageInHrs(hours);
        consumption.setUsage(hours * unit);
        device.setDeviceOn(false);
        deviceRepo.save(device);
        return ResponseEntity.ok(consumption);
    }

    public ResponseEntity<String> deleteDevice(long deviceId) {
        if(!deviceRepo.existsById(deviceId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid device ID. No device found");
        }
        DeviceEntity device = deviceRepo.findById(deviceId).get();
        deviceRepo.delete(device);
        return ResponseEntity.ok("Successfully deleted: \n" + device.toString());
    }

    public ResponseEntity<?> filteredDevices(DeviceType type, Date FromDate, Date ToDate) {
        List<Object> response = new ArrayList<>();
        if(type == null || type.equals(DeviceType.Electric)) {
            List<ElectricEntity> electricDevices = electricRepo.findByOrderByDevice_InstallationDate();
            for (ElectricEntity electricEntity : electricDevices) {
                if(isWithinDateRange(electricEntity.getDevice().getInstallationDate(), FromDate, ToDate)) {
                    response.add(electricEntity);
                }
            }
        } 
        if(type == null || type.equals(DeviceType.Water)) {
            List<WaterEntity> waterDevices = waterRepo.findByOrderByDevice_InstallationDate();
            for (WaterEntity waterEntity : waterDevices) {
                if(isWithinDateRange(waterEntity.getDevice().getInstallationDate(), FromDate, ToDate)) {
                    response.add(waterEntity);
                }
            }
        }
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No device found");
        }
        
        Collections.sort(response, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                String name1 = getDeviceName(o1);
                String name2 = getDeviceName(o2);
                return name1.compareToIgnoreCase(name2);
            }
        });
        
        return ResponseEntity.ok(response);
    }

    
    private String getDeviceName(Object device) {
        if (device instanceof ElectricEntity) {
            return ((ElectricEntity) device).getDevice().getDeviceName();
        } else if (device instanceof WaterEntity) {
            return ((WaterEntity) device).getDevice().getDeviceName();
        } else {
            return "";
        }
    }

    private boolean isWithinDateRange(Date current, Date FromDate, Date ToDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        current = cal.getTime();
        if(FromDate == null && ToDate == null) {
            return true;
        } else if(FromDate != null && ToDate != null) {
            if((current.equals(FromDate) || current.equals(ToDate)) || 
                (current.after(FromDate) && current.before(ToDate))) {
                    return true;
                }
        } else if(FromDate == null) {
            if(current.before(ToDate) || current.equals(ToDate)) {
                return true;
            }
        } else if(ToDate == null) {
            if(current.after(FromDate) || current.equals(FromDate)) {
                return true;
            }
        } 
        return false;
    }
}
