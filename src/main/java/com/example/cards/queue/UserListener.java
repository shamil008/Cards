package com.example.cards.queue;

import com.example.cards.model.queue.ChangeCardStatusDto;
import com.example.cards.service.abstraction.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.smartcardio.Card;

@Component
@RequiredArgsConstructor
public class UserListener {
    private final CardService cardService;
    private final ObjectMapper objectMapper;
    @SneakyThrows
    @RabbitListener(queues = "USER_Q")
    public void consume(String message){
        var data = objectMapper.readValue(message, ChangeCardStatusDto.class);
        cardService.changeCardStatus(data.getId());
    }
}
