package com.BrainWorks.Logging.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("welcome")
public class WelcomeController {

    Logger logger= LoggerFactory.getLogger(WelcomeController.class);
    @GetMapping("/")
    public String welcomeMsg(){
        logger.info("Debugging is start");
        String msg="Welcome to Pune";
        try{
            int a=10/0;
        }
        catch (Exception e){
            logger.error("error "+e.getMessage());
        }
        logger.info("Debugging is End");
        return msg;
    }
}
