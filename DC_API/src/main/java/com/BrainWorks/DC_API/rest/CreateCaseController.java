package com.BrainWorks.DC_API.rest;

import com.BrainWorks.DC_API.binding.CreateCaseResponse;
import com.BrainWorks.DC_API.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CreateCaseController {
    
    @Autowired
    private DcService dcService;

    @GetMapping("/case/{appId}")
    public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer appId)
    {
        Long caseNum = dcService.loadCaseNum(appId);
        Map<Integer, String> allPlan = dcService.getAllPlan();
        CreateCaseResponse response=new CreateCaseResponse();
        response.setCaseNum(caseNum);
        response.setPlanNames(allPlan);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
