package com.example.cards.client.decoder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JsonNodeFiledName {
    MESSAGE("message"),
    CODE("code");
    private final String value;
}
