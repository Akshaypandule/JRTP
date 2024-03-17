package com.BrainWorks.DC_API.rest;

import com.BrainWorks.DC_API.binding.EducationBinding;
import com.BrainWorks.DC_API.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EducationRestController {
    @Autowired
    private DcService dcService;
    @PostMapping("/education")
    public ResponseEntity<Long> saveEducation(@RequestBody EducationBinding educationBinding)
    {
        Long caseNum = dcService.saveEducation(educationBinding);
        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }
}
