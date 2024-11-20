package org.example.tasty_rest.service;


import lombok.RequiredArgsConstructor;
import org.example.tasty_rest.dto.CustomerRequest;
import org.example.tasty_rest.entity.Customer;
import org.example.tasty_rest.mapper.CustomerMapper;
import org.example.tasty_rest.repo.CustomerRepo;
import org.example.tasty_rest.repo.CustomerUpdateRepo;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {
    // To store entity into sql database
    private final CustomerUpdateRepo repo;

    // To convert dto to entity
    private final CustomerUpdateMapper updateMapper;
    private final CustomerMapper createMapper;
    private final CustomerDeleteMapper deleteMapper;

    private final EncryptionService encryptionService;

    public Customer createCustomer(CustomerRequest request) {
        System.out.println("==================== create service");

        // This will convert our dto to entity using Mapper
        Customer customer = createMapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        // Stores entity into database using Repo
        repo.save(customer);
        return customer;
    }

    public Customer deleteCustomer(CustomerDeleteRequest request) {
        System.out.println("==================== delete service");

        Optional<Customer> existingCustomerOpt = repo.findByEmail(request.getEmail());

        if (existingCustomerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found with email: " + request.getEmail());
        }

        Customer existingCustomer = existingCustomerOpt.get();

        // Convert the DTO to an entity using Mapper
        Customer customer = deleteMapper.toEntity(request);

        // deletes entity into database using Repo
        repo.delete(existingCustomer);
        return customer;
    }

    public Customer updateCustomer(CustomerUpdateRequest request) {
        System.out.println("==================== update service");

        // Retrieve the existing customer from the database
        Optional<Customer> existingCustomerOpt = repo.findByEmail(request.getEmail());

        if (existingCustomerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found with email: " + request.getEmail());
        }

        Customer existingCustomer = existingCustomerOpt.get();

        // Convert the DTO to an entity using Mapper
        Customer updatedCustomer = updateMapper.toEntity(request);

        // Compare each field dynamically and update only the changed fields
        Field[] fields = Customer.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object oldValue = field.get(existingCustomer);
                Object newValue = field.get(updatedCustomer);

                if (newValue != null && (oldValue == null || !newValue.equals(oldValue))) {
                    System.out.println(field.getName() + " changed from " + oldValue + " to " + newValue);
                    field.set(existingCustomer, newValue); // Update the field in the existing customer
                }
            } catch (IllegalAccessException e) {
                System.err.println("Error accessing field: " + field.getName());
            }
        }

        // Save the updated entity
        repo.save(existingCustomer);
        return existingCustomer;
    }
}