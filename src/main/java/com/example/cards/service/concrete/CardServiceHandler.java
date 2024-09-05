package com.example.cards.service.concrete;

import com.example.cards.aspect.LogIgnore;
import com.example.cards.aspect.Loggable;
import com.example.cards.client.UserClient;
import com.example.cards.dao.repository.CardRepository;
import com.example.cards.mapper.CardMapper;
import com.example.cards.model.request.CardRequest;
import com.example.cards.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.cards.mapper.CardMapper.CARD_MAPPER;
import static com.example.cards.model.enums.CardStatus.ACTIVE;
import static com.example.cards.model.enums.CardType.DEBIT;
@Slf4j
@Service
@RequiredArgsConstructor
@Loggable
public class CardServiceHandler implements CardService {
    private final CardRepository cardRepository;
    private final UserClient userClient;
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

    @Override
    public void createCard(CardRequest request) {
        var user = userClient.getUser(5L);
        log.info("User is:{}",user.getId());
        cardRepository.save(CARD_MAPPER.buildCardEntity(request));
    }
}
