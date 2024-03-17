package com.BrainWorks.CO_API.rest;

import com.BrainWorks.CO_API.binding.CoResponse;
import com.BrainWorks.CO_API.service.CoService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CoApiController {

    private CoService coService;

    @GetMapping("/process")
    public CoResponse processTrigger() throws MessagingException, IOException {
        return coService.processOnPendingTrigger();
    }
}
