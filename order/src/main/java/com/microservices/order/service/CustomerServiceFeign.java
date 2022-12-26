package com.microservices.order.service;

import com.microservices.order.client.CustomerFeign;
import com.microservices.order.client.ProductFeign;
import com.microservices.order.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceFeign {

    @Autowired
    private final CustomerFeign customerFeign;

    public CustomerServiceFeign(CustomerFeign customerFeign){
        this.customerFeign = customerFeign;
    }

    public ResponseEntity<?> list(){
        return customerFeign.list();
    }

    public ResponseEntity<?> getById(Integer id){
        return ResponseEntity.ok(customerFeign.getById(id));
    }

}
