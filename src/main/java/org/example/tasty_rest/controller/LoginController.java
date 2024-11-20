package org.example.tasty_rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tasty_rest.dto.LoginRequest;
import org.example.tasty_rest.service.CustomerService;
import org.example.tasty_rest.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(loginService.loginUser(request));
    }
}