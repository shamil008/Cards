package com.example.cards.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


@Configuration
public class RabbitMQConfig {
    private final String publisherQ;
    private final String publisherDLQ;
    private final String publisherQExchange;
    private final String publisherDLQExchange;
    private final String publisherQKey;
    private final String publisherDLQKey;

    public RabbitMQConfig(@Value("${rabbitmq.queue.user}") String publisherQ,
                                 @Value("${rabbitmq.queue.user-dlq}") String publisherDLQ) {
        this.publisherQ = publisherQ;
        this.publisherDLQ = publisherDLQ;
        this.publisherQExchange = publisherQ + "_EXCHANGE";
        this.publisherDLQExchange = publisherDLQ + "_EXCHANGE";
        this.publisherQKey = publisherQ + "_KEY";
        this.publisherDLQKey = publisherDLQ + "_KEY";
    }

    @Bean
    DirectExchange publisherDLQExchange() {
        return new DirectExchange(publisherDLQExchange);
    }

    @Bean
    DirectExchange publisherQExchange() {
        return new DirectExchange(publisherQExchange);
    }

    @Bean
    Queue publisherDLQ() {
        return QueueBuilder.durable(publisherDLQ).build();
    }

    @Bean
    Queue publisherQ() {
        return QueueBuilder.durable(publisherQ)
                .withArgument("x-dead-letter-exchange", publisherDLQExchange)
                .withArgument("x-dead-letter-routing-key", publisherDLQKey)
                .build();
    }

    @Bean
    Binding publisherDLQBinding() {
        return BindingBuilder.bind(publisherDLQ())
                .to(publisherDLQExchange()).with(publisherDLQKey);
    }

    @Bean
    Binding publisherQBinding() {
        return BindingBuilder.bind(publisherQ())
                .to(publisherQExchange()).with(publisherQKey);
    }
}
