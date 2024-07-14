package com.ecowatch.ecowatch.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
import com.ecowatch.ecowatch.Models.Consumption.ConsumptionRepo;

@Service
public class ConsumptionService {
    @Autowired
    private ConsumptionRepo consumptionRepo;
    public ResponseEntity<?> addConsumption() {
        // ConsumptionEntity entity = new ConsumptionEntity(2, 2, LocalDateTime.now(ZoneId.of("Asia/Manila")) , null);
        // consumptionRepo.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
