package com.hakimov.supplier.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {
    private int id;

    @NotEmpty(message = "Enter organisation")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 char")
    private String organisation;
    @NotEmpty(message = "Enter address")
    @Size(min = 2, max = 100, message = "Address should be between 2 and 100 char")
    private String address;
}
