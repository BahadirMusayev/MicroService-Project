package com.example.customer.controller;

import com.example.customer.model.CustomerDto;
import com.example.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public void saveCustomer(@RequestBody CustomerDto customerDto){
        customerService.saveCustomer(customerDto);
    }

    @GetMapping("/get/by/{finCode}")
    public CustomerDto getByFinCode(@PathVariable String finCode){
        return customerService.getByFinCode(finCode);
    }
}
