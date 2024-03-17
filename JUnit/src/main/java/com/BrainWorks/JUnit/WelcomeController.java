package com.BrainWorks.JUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;


    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMsg(){
        String s = welcomeService.welcomeMsg();
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
