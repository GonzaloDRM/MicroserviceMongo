package com.microservices.customer.controller;

import com.microservices.customer.model.Customer;
import com.microservices.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Customer customer){
        Map<String, String> resp = new HashMap<>();
        customerService.save(customer);
        resp.put("Response", "Upload successful");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(customerService.listAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(customerService.getById(id));
    }

}
