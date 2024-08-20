package com.devsu.apirest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPatchDTO {
    private String name;

    private String gender;

    private Integer age;

    private String identification;

    private String address;

    private String phone;

    private String password;

    private Boolean status;
}
