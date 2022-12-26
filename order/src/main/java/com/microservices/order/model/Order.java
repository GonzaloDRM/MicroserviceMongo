package com.microservices.order.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "order")
public class Order {

    @Id
    private Integer id;

    private String state;
    private Date creationDate;
    private Date updateDate;
    private String paymentMethod;
    private Integer customer;
    private List<Integer> products;

}
