package com.example.contacts.dao.repository;

import com.example.contacts.dao.entity.ContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactsEntity, Integer> {
    ContactsEntity findByCustomerID(Integer customerID);
}
