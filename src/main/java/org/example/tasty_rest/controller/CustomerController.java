package org.example.tasty_rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tasty_rest.dto.CustomerRequest;
import org.example.tasty_rest.entity.Customer;
import org.example.tasty_rest.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/create_account")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        System.out.println("----------Create Controller---------");
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PutMapping("/update") // for post request
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerUpdateRequest request) {
        System.out.println("----------Update Controller----------");
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping("/remove_account") // for post request
    public ResponseEntity<Customer> deleteCustomer(@RequestBody @Valid CustomerDeleteRequest request) {
        System.out.println("----------Delete Controller----------");
        return ResponseEntity.ok(customerService.deleteCustomer(request));
    }

}