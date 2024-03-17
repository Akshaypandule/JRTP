package com.BrainWorks.DC_API.rest;

import com.BrainWorks.DC_API.binding.ChildRequest;
import com.BrainWorks.DC_API.binding.DCSummery;
import com.BrainWorks.DC_API.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildRestController {

    @Autowired
    private DcService dcService;
    @PostMapping("/child")
    public ResponseEntity<DCSummery> saveChild(@RequestBody ChildRequest request){
        Long caseNum = dcService.saveChildren(request);
        DCSummery summery = dcService.getSummery(caseNum);
        return new ResponseEntity<>(summery, HttpStatus.OK);
    }
}
