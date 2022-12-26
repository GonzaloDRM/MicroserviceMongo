package com.microservices.customer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customer")
public class Customer {

    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;

}
