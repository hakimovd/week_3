package com.hakimov.owner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Owner {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max=30, message = "Name should be between 2 and 30 char")
    private String name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max=30, message = "Name should be between 2 and 30 char")
    private String surname;


    @NotNull(message = "Email should not be empty")
    private long phone;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

}
