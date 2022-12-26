package com.microservices.order.dto;

import com.microservices.order.model.Customer;
import com.microservices.order.model.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private Integer id;
    private String state;
    private Date creationDate;
    private Date updateDate;
    private String paymentMethod;
    private Customer customer;
    private List<Product> products;

}
