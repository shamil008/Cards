package com.example.cards.model.response;

import com.example.cards.model.enums.CardStatus;
import com.example.cards.model.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponse {
    private Long id;

    private String holder;

    private CardType type;

    private BigDecimal balance;

    private LocalDateTime expiry;

    private CardStatus status;
}
