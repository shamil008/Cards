package com.example.cards.dao.entity;

import com.example.cards.model.enums.CardStatus;
import com.example.cards.model.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String holder;

    @Enumerated(EnumType.STRING)
    private CardType type;

    private BigDecimal balance;

    private LocalDateTime expiry;

    @Enumerated(EnumType.STRING)
    private CardStatus status;
}
