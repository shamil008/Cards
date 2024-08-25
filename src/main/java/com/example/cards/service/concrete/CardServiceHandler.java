package com.example.cards.service.concrete;

import com.example.cards.dao.repository.CardRepository;
import com.example.cards.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.cards.model.enums.CardStatus.ACTIVE;
import static com.example.cards.model.enums.CardType.DEBIT;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceHandler implements CardService {
    private final CardRepository cardRepository;
    @Override
    public void interestRateIncrease() {
        log.info("ActionLog.interestRateIncrease.start");
        var cards = cardRepository.findByTypeAndStatus(DEBIT,ACTIVE);
        cards.forEach(it ->{
            var balance = it.getBalance();
            var interestRate = new BigDecimal("0.05");
            balance = balance.add(balance.multiply(interestRate));
            it.setBalance(balance);
        });
        cardRepository.saveAll(cards);
        log.info("ActionLog.interestRateIncrease.success");

    }
}
