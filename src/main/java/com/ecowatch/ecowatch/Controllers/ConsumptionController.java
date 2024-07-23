package com.ecowatch.ecowatch.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Consumption.ConsumptionEntity;
import com.ecowatch.ecowatch.Service.ConsumptionService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping(path="/api/consumption")
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;

    // @PostMapping
    // @Operation(summary = "Add a consumption record")
    // @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ConsumptionEntity.class)))
    // public ResponseEntity<?> addConsumption() {
    //     return consumptionService.addConsumption();
    // }
}
