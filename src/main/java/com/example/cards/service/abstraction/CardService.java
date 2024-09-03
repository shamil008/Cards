package com.example.cards.service.abstraction;

import com.example.cards.model.request.CardRequest;

public interface CardService {
    void interestRateIncrease();

    void createCard(CardRequest request);

}
