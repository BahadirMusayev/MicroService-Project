package com.example.customer.service;

import com.example.customer.dao.entity.CustomerEntity;
import com.example.customer.dao.exception.FoundException;
import com.example.customer.dao.exception.NotFoundException;
import com.example.customer.dao.repository.CustomerRepository;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.model.ContactsDto;
import com.example.customer.model.CustomerDto;

import com.example.customer.producer.MessageProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final MessageProducer messageProducer;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, MessageProducer messageProducer){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.messageProducer = messageProducer;
    }

    @Transactional
    public void saveCustomer(CustomerDto customerDto) {
        System.out.println("Save Customer Started... ");
        CustomerEntity customerEntity = customerMapper.mapDtoToEntity(customerDto);
        CustomerEntity finCode = customerRepository.findByFinCode(customerEntity.getFinCode());
        if(finCode != null){
            throw new FoundException("Customer is also Found !");
        }
        customerRepository.save(customerEntity);

        ContactsDto contactsDto = customerDto.getContactsDto();
        contactsDto.setCustomerID(customerEntity.getId());
        messageProducer.sendMessage(contactsDto);
    }

    public CustomerDto getByFinCode(String finCode){
        CustomerEntity customerEntity = customerRepository.
                findByFinCode(finCode);
        if(customerEntity == null){
            throw new NotFoundException("Customer Not Found !");
        }
        CustomerDto customerDto = customerMapper.mapEntityToDto(customerEntity);

        messageProducer.sendMessage(customerEntity.getId());
        ContactsDto contactsDto = messageProducer.sendMessage(customerEntity.getId());
        customerDto.getContactsDto().setCustomerID(contactsDto.getCustomerID());
        customerDto.getContactsDto().setTel(contactsDto.getTel());
        customerDto.getContactsDto().setEmail(contactsDto.getEmail());
        return customerDto;
    }
}