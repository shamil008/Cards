package com.example.cards.service.abstraction;

import com.example.cards.model.request.CardRequest;
import com.example.cards.model.response.CardResponse;

public interface CardService {
    void interestRateIncrease();

    void createCard(CardRequest request);

    CardResponse getCard(Long id);

    void changeCardStatus(Long id);


}
