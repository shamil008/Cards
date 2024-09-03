package com.example.cards.model.request;

import com.example.cards.model.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private String holder;

    private BigDecimal balance;

    private LocalDateTime expiry;
}
