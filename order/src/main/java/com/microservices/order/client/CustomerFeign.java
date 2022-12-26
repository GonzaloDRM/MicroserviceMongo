package com.microservices.order.client;

import com.microservices.order.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "customer", url = "http://localhost:8001")
public interface CustomerFeign {

    @GetMapping("/list")
    public ResponseEntity<?> list();

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Customer customer);

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id);

}
