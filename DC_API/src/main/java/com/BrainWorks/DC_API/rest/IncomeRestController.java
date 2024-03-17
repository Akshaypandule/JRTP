package com.BrainWorks.DC_API.rest;

import com.BrainWorks.DC_API.binding.IncomeBinding;
import com.BrainWorks.DC_API.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomeRestController {

    @Autowired
    private DcService dcService;


    @PostMapping("/income")
    public ResponseEntity<Long> saveIncomeDetails(@RequestBody IncomeBinding income){
        Long caseNum = dcService.saveIncome(income);
        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }
}
