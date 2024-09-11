package com.example.cards.model;

import com.example.cards.model.enums.CardStatus;
import com.example.cards.model.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheData implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long id;

    private String holder;


    private CardType type;

    private BigDecimal balance;

    private LocalDateTime expiry;

    private CardStatus status;
}
