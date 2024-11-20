package org.example.tasty_rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerUpdateRequest{
    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("email")
    String email;

    @Size(min = 6, max = 12)
    @JsonProperty("password")
    String password;
}
