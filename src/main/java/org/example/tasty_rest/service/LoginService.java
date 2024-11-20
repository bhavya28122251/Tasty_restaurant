package org.example.tasty_rest.service;

import lombok.RequiredArgsConstructor;
import org.example.tasty_rest.dto.LoginRequest;
import org.example.tasty_rest.entity.Customer;
import org.example.tasty_rest.helper.EncryptionService;
import org.example.tasty_rest.helper.JWTHelper;
import org.example.tasty_rest.mapper.LoginMapper;
import org.example.tasty_rest.repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepo repo;
    private final LoginMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String loginUser(LoginRequest request) {
        Customer customer = mapper.toEntity(request);
        // Attempt to find the customer in the database
        Optional<Customer> existingCustomer = repo.findByEmail(customer.getEmail());

        if (existingCustomer.isPresent()) {
            // Check if the password matches
            if(!encryptionService.validates(request.password(), existingCustomer.get().getPassword())) {
                return "Wrong Password or Email";
            }
            else {
                return jwtHelper.generateToken(request.email());
            }
        } else {
            return "User not found";
        }
    }
}