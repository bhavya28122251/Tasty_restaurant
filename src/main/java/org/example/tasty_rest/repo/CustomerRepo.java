package org.example.tasty_rest.repo;
import java.util.Optional;

import org.example.tasty_rest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}