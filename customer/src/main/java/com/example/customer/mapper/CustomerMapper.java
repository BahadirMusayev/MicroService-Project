package com.example.customer.mapper;

import com.example.customer.dao.entity.CustomerEntity;
import com.example.customer.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "finCode", source = "finCode")
    CustomerEntity mapDtoToEntity(CustomerDto customerDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "finCode", source = "finCode")
    CustomerDto mapEntityToDto(CustomerEntity customerEntity);

    List<CustomerDto> mapEntityToDtos(List<CustomerEntity> customerEntities);
}
