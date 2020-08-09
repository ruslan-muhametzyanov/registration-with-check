package org.example.registration_with_check.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class SendMailerStub implements SendMailer {

    private static final Logger log = LoggerFactory.getLogger(SendMailerStub.class);

    @Override
    public void sendMail(String toAddress, String messageBody) throws TimeoutException {
        if(Utility.shouldThrowTimeout()) {
            Utility.sleep();

            throw new TimeoutException("Timeout!");
        }

        if(Utility.shouldSleep()) {
            Utility.sleep();
        }

        // ok.
        log.info("Message sent to {}, body {}.", toAddress, toAddress);
    }
}