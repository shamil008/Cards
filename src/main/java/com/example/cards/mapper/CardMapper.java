package com.example.cards.mapper;

import com.example.cards.dao.entity.CardEntity;
import com.example.cards.model.enums.CardStatus;
import com.example.cards.model.enums.CardType;
import com.example.cards.model.request.CardRequest;

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
}
