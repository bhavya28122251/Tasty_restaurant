package org.example.tasty_rest.service;


import lombok.RequiredArgsConstructor;
import org.example.tasty_rest.dto.CustomerRequest;
import org.example.tasty_rest.entity.Customer;
import org.example.tasty_rest.mapper.CustomerMapper;
import org.example.tasty_rest.repo.CustomerRepo;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}