package com.example.cards.controller;

import com.example.cards.model.request.CardRequest;
import com.example.cards.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/cards")
public class CardController {
    private final CardService cardService;
    @PostMapping
    @ResponseStatus(CREATED)
    public void createCard(@RequestBody CardRequest request){
        cardService.createCard(request);

    }
}
