package com.example.contacts.mapper;

import com.example.contacts.dao.entity.ContactsEntity;
import com.example.contacts.model.ContactsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactsMapper {
    @Mapping(target = "tel", source = "tel")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "customerID", source = "customerID")
    ContactsEntity mapDtoToEntity(ContactsDto contactsDto);

    @Mapping(target = "tel", source = "tel")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "customerID", source = "customerID")
    ContactsDto mapEntityToDto(ContactsEntity contactsEntity);

    List<ContactsDto> mapEntityToDtos(List<ContactsEntity> contactsEntities);
}
