package com.example.contacts.producer;

import com.example.contacts.model.ContactsDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ContactsDto contactsDto) {
        rabbitTemplate.convertAndSend("contactQueue", "contactRoutingKey", contactsDto);
    }
}
