package com.BrainWorks.CO_API.service;

import com.BrainWorks.CO_API.binding.CoResponse;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CoService {

    public CoResponse processOnPendingTrigger() throws IOException, MessagingException;
}
