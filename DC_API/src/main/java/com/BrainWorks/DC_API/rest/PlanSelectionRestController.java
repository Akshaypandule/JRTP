package com.BrainWorks.DC_API.rest;

import com.BrainWorks.DC_API.binding.PlanSelectionBinding;
import com.BrainWorks.DC_API.service.DcService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanSelectionRestController {

    @Autowired
    private DcService dcService;


    @PostMapping("/plansel")
    public ResponseEntity<Long> planSelection(@RequestBody PlanSelectionBinding planSel)
    {
        Long caseNum = dcService.savePlanSelection(planSel);

        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }

}
