package com.Brainworks.CitizenAppEntity.rest;

import com.Brainworks.CitizenAppEntity.binding.CitizenAppBinding;
import com.Brainworks.CitizenAppEntity.service.CitizenAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArController {

    @Autowired
    private CitizenAppService citizenAppService;

    @PostMapping("/app")
    public ResponseEntity<String> createCitizen(@RequestBody CitizenAppBinding citizenApp){

        Integer citizen = citizenAppService.createCitizen(citizenApp);

        if (citizen>0){
            return new ResponseEntity<>("App is created with App Id: "+citizen, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Invalid SSN",HttpStatus.BAD_REQUEST);
        }
    }
}
