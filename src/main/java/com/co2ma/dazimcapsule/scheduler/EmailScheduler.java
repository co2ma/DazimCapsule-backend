package com.co2ma.dazimcapsule.scheduler;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailBatchService emailBatchService;

    @Scheduled(cron = "0 30 0 * * *")
    public void sendEmail() {
        try {
            emailBatchService.run(0);
        }catch(MessagingException e){
            log.error("이메일 전송 오류: {}", e.getMessage());
        }
    }

}
