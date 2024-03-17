package com.BrainWorks.WelcomeApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Greet-API")
public interface GreetClient
{
    @GetMapping("greet")
    public String GreetApi();
}
