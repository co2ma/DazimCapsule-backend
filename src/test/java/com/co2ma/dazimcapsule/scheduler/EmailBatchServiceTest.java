package com.co2ma.dazimcapsule.scheduler;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailBatchServiceTest {
    @Autowired
    private EmailBatchService emailBatchService;

    @Test
    public void run() throws MessagingException {
        emailBatchService.run(3);
    }
}
