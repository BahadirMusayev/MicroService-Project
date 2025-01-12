package com.example.contacts.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue contactQueue() {
        return new Queue("contactQueue", true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("contactQueue");
    }

    @Bean
    public Binding binding(Queue contactQueue, Exchange contactExchange) {
        return BindingBuilder.bind(contactQueue)
                .to(contactExchange)
                .with("contactRoutingKey")
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}