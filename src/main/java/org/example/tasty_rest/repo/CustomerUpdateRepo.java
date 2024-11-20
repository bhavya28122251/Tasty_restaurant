package org.example.tasty_rest.repo;

import org.example.tasty_rest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerUpdateRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
