package com.microservices.order.model;

import lombok.Data;

@Data
public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

}
