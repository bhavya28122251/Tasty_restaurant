package org.example.tasty_rest.mapper;

import org.example.tasty_rest.dto.CustomerDeleteRequest;
import org.example.tasty_rest.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeleteMapper {
    public Customer toEntity(CustomerDeleteRequest request) {
        return Customer.builder()
                .email(request.getEmail())
                .build();
    }
}