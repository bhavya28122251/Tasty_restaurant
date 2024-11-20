package org.example.tasty_rest.mapper;

import org.example.tasty_rest.dto.LoginRequest;
import org.example.tasty_rest.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    public Customer toEntity(LoginRequest request) {
        return Customer.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}