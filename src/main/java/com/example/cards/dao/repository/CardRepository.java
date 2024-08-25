package com.example.cards.dao.repository;

import com.example.cards.dao.entity.CardEntity;
import com.example.cards.model.enums.CardStatus;
import com.example.cards.model.enums.CardType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<CardEntity,Long> {
    List<CardEntity> findByTypeAndStatus(CardType cardTypes, CardStatus cardStatus);
}
