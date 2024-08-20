package com.devsu.apirest.dto.customer;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerDTO {
    private Long customerId;
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Gender is required.")
    private String gender;

    @NotNull(message = "Age is required.")
    private Integer age;

    @NotBlank(message = "Identification is required.")
    private String identification;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotBlank(message = "Phone is required.")
    private String phone;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotNull(message = "Status is required.")
    private Boolean status;
}
