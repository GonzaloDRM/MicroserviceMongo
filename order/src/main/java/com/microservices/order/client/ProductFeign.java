package com.microservices.order.client;

import com.microservices.order.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product", url = "localhost:8003")
public interface ProductFeign {

    @GetMapping("/list")
    public ResponseEntity<?> list();

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Product product);

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id);

}
