package com.example.cards.mapper;

import com.example.cards.dao.entity.CardEntity;
import com.example.cards.model.CacheData;
import com.example.cards.model.enums.CardStatus;
import com.example.cards.model.enums.CardType;
import com.example.cards.model.request.CardRequest;
import com.example.cards.model.response.CardResponse;

import static com.example.cards.model.enums.CardStatus.ACTIVE;
import static com.example.cards.model.enums.CardType.DEBIT;

public enum CardMapper {
    CARD_MAPPER;

    public CardEntity buildCardEntity(CardRequest request){
        return CardEntity.builder()
                .holder(request.getHolder())
                .type(DEBIT)
                .balance(request.getBalance())
                .expiry(request.getExpiry())
                .status(ACTIVE)
                .build();
    }
    public CardResponse buildCardResponse(CardEntity cardEntity){
        return CardResponse.builder()
                .id(cardEntity.getId())
                .holder(cardEntity.getHolder())
                .type(cardEntity.getType())
                .balance(cardEntity.getBalance())
                .expiry(cardEntity.getExpiry())
                .status(cardEntity.getStatus())
                .build();
    }
    public CardResponse buildCardResponse(CacheData cacheData){
        return CardResponse.builder()
                .id(cacheData.getId())
                .holder(cacheData.getHolder())
                .type(cacheData.getType())
                .balance(cacheData.getBalance())
                .expiry(cacheData.getExpiry())
                .status(cacheData.getStatus())
                .build();
    }
}
