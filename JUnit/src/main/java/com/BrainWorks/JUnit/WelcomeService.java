package com.BrainWorks.JUnit;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public String welcomeMsg(){
        String msg="Welcome to AshokIt";
        return msg;
    }
}
