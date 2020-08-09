package org.example.registration_with_check.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class MessagingServiceStub implements MessagingService {

    private static final Logger log = LoggerFactory.getLogger(MessagingServiceStub.class);

    @Override
    public String send(String msg) {
        return String.valueOf(UUID.randomUUID());
    }

    @Override
    public Boolean receive(String messageId) throws TimeoutException {
        if(Utility.shouldThrowTimeout()) {
            Utility.sleep();

            throw new TimeoutException("Timeout!");
        }

        if(Utility.shouldSleep()) {
            Utility.sleep();
        }

        Boolean valid = new Random().nextInt(10) > 2;
        log.info("User is valid: {}", valid);
        return valid;
    }
}
