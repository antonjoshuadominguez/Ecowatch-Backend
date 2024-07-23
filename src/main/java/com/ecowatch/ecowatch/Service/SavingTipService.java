package com.ecowatch.ecowatch.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecowatch.ecowatch.Models.SavingTips.SavingTipEntity;
import com.ecowatch.ecowatch.Models.SavingTips.SavingTipRepo;

@Service
public class SavingTipService {
    @Autowired
    private SavingTipRepo savingTipRepo; 
    private boolean[] renderedTips;

    public SavingTipEntity getRandomTip() {
        List<SavingTipEntity> savingTips = savingTipRepo.findAll();
        if(renderedTips == null) {
            renderedTips = new boolean[savingTips.size()];
            Arrays.fill(renderedTips, false);
        }
        Random random = new Random();
        int randomInt = 0;
        boolean isAllRendered = true;

        for (Boolean state : renderedTips) {
            if(state != true) isAllRendered = false;
        }

        if(isAllRendered) {
            Arrays.fill(renderedTips, false);
        }

        do {
            randomInt = random.nextInt(savingTips.size());
        } while (renderedTips[randomInt] == true);

        renderedTips[randomInt] = true;
        return savingTips.get(randomInt);
    }
}
