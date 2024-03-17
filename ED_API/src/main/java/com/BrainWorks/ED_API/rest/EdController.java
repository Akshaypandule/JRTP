package com.BrainWorks.ED_API.rest;

import com.BrainWorks.ED_API.binding.ElgResponse;
import com.BrainWorks.ED_API.service.ElgDtlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdController {

    @Autowired
    private ElgDtlsService elgDtlsService;

    @GetMapping("/eligibility/{caseNum}")
    public ResponseEntity<ElgResponse> edController(@PathVariable Long caseNum)
    {
        ElgResponse response = elgDtlsService.determineEligibility(caseNum);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
