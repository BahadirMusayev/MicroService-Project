package com.example.customer.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {
    private String name;
    private String surname;
    private Integer age;
    private String finCode;
    private ContactsDto contactsDto;
}
