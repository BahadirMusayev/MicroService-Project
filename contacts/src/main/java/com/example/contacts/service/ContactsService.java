package com.example.contacts.service;

import com.example.contacts.dao.entity.ContactsEntity;
import com.example.contacts.dao.repository.ContactsRepository;
import com.example.contacts.mapper.ContactsMapper;
import com.example.contacts.model.ContactsDto;
import com.example.contacts.producer.MessageProducer;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {
    private final ContactsRepository contactsRepository;

    private final ContactsMapper contactsMapper;

    private final MessageProducer messageProducer;

    public ContactsService(ContactsRepository contactsRepository, ContactsMapper contactsMapper, MessageProducer messageProducer){
        this.contactsRepository = contactsRepository;
        this.contactsMapper = contactsMapper;
        this.messageProducer = messageProducer;
    }

    @Transactional
    @RabbitListener(queues = "contactQueue")
    public void saveContact(ContactsDto contactsDto){
        System.out.println("Save Contact Started... ");
        ContactsEntity contactsEntity = contactsMapper.mapDtoToEntity(contactsDto);
        ContactsEntity customerID = contactsRepository.findByCustomerID(contactsDto.getCustomerID());
        if(customerID != null) {
            ContactsEntity contact = new ContactsEntity();
            contact.setCustomerID(contactsEntity.getCustomerID());
            contact.setTel(contactsEntity.getTel());
            contact.setEmail(contactsEntity.getEmail());
            contactsRepository.save(contact);
        }else{
            contactsRepository.save(contactsMapper.mapDtoToEntity(contactsDto));
        }
    }

    @RabbitListener(queues = "contactQueue")
    public ContactsDto getByID(Integer customerID){
        ContactsEntity contactsEntity = contactsRepository.findByCustomerID(customerID);
        ContactsDto contactsDto = contactsMapper.mapEntityToDto(contactsEntity);
        return contactsDto;
    }
}