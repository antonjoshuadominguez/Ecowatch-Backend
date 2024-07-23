package com.ecowatch.ecowatch.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.SavingTips.SavingTipEntity;
import com.ecowatch.ecowatch.Service.SavingTipService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/api/saving-tip")
public class SavingTipController {
    @Autowired
    private SavingTipService savingTipService;

    @Operation(summary = "Get random saving tip")
    @GetMapping()
    public SavingTipEntity getRandomTip() {
        return savingTipService.getRandomTip();
    }
}
