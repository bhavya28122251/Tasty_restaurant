package org.example.tasty_rest.mapper;

import org.example.tasty_rest.dto.CustomerRequest;
import org.example.tasty_rest.dto.CustomerResponse;
import org.example.tasty_rest.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }

}