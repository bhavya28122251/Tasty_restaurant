package org.example.tasty_rest.mapper;

import org.example.tasty_rest.dto.CustomerUpdateRequest;
import org.example.tasty_rest.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdateMapper {
    public Customer toEntity(CustomerUpdateRequest request) {
        return Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
