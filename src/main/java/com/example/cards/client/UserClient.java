package com.example.cards.client;

import com.example.cards.client.decoder.CustomErrorDecoder;
import com.example.cards.model.client.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user",url = "http://localhost:8080",configuration = CustomErrorDecoder.class)
public interface UserClient {
    @GetMapping("/internal/v1/users/{id}")
    UserResponseDto getUser(@PathVariable Long id);
}
