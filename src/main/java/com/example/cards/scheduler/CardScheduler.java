package com.example.cards.scheduler;

import com.example.cards.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RequiredArgsConstructor
public class CardScheduler {
    private final CardService cardService;
    @Scheduled(cron = "* * 3 * * *")
    @SchedulerLock(name = "interestRateIncreaseCardBalance",lockAtLeastForString = "PT1M",lockAtMostForString = "PT3M")
    public void interestRateIncreaseCardBalance(){
        cardService.interestRateIncrease();
    }
}
