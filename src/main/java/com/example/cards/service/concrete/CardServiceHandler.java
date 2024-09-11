package com.example.cards.service.concrete;

import com.example.cards.aspect.LogIgnore;
import com.example.cards.aspect.Loggable;
import com.example.cards.client.UserClient;
import com.example.cards.dao.repository.CardRepository;
import com.example.cards.exception.NotFoundException;
import com.example.cards.mapper.CardMapper;
import com.example.cards.model.CacheData;
import com.example.cards.model.enums.ExceptionConstants;
import com.example.cards.model.request.CardRequest;
import com.example.cards.model.response.CardResponse;
import com.example.cards.service.abstraction.CacheService;
import com.example.cards.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.cards.mapper.CardMapper.CARD_MAPPER;
import static com.example.cards.model.enums.CardStatus.ACTIVE;
import static com.example.cards.model.enums.CardType.DEBIT;
import static com.example.cards.model.enums.ExceptionConstants.CARD_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@Loggable
public class CardServiceHandler implements CardService {
    private final CardRepository cardRepository;
    private final UserClient userClient;
    private final CacheService cacheService;
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
//        var user = userClient.getUser(5L);
//        log.info("User is:{}",user.getId());
        var savedCard = cardRepository.save(CARD_MAPPER.buildCardEntity(request));
        cacheService.saveCache(CacheData.builder()
                        .id(savedCard.getId())
                        .holder(savedCard.getHolder())
                        .type(savedCard.getType())
                        .balance(savedCard.getBalance())
                        .expiry(savedCard.getExpiry())
                        .status(savedCard.getStatus())
                    .build());
    }

    @Override
    public CardResponse getCard(Long id) {
        try {
            var cacheData = cacheService.getBucket(id);
            if(cacheData != null)
                return CARD_MAPPER.buildCardResponse(cacheData);
        }
        catch (Exception e) {

        }

        var card = cardRepository.findById(id).orElseThrow(()->new NotFoundException(CARD_NOT_FOUND.getCode(),CARD_NOT_FOUND.getMessage()));
        cacheService.saveCache(CacheData.builder()
                        .id(card.getId())
                        .holder(card.getHolder())
                        .type(card.getType())
                        .balance(card.getBalance())
                        .expiry(card.getExpiry())
                        .status(card.getStatus())
                .build());
        return CARD_MAPPER.buildCardResponse(card);


    }
}
