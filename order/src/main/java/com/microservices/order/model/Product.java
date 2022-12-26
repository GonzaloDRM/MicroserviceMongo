package com.microservices.order.model;

import lombok.Data;

@Data
public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Integer stock;

}
