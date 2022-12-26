package com.microservices.order.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.microservices.order.model.Customer;
import com.microservices.order.model.Order;
import com.microservices.order.service.CustomerServiceFeign;
import com.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private final OrderService orderService;
    private final CustomerServiceFeign customerServiceFeign;

    public OrderController(OrderService orderService, CustomerServiceFeign customerServiceFeign){
        this.orderService = orderService;
        this.customerServiceFeign = customerServiceFeign;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Order order){
        Map<String, String> resp = new HashMap<>();
        orderService.save(order);
        resp.put("Response", "Upload successful");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() throws JsonMappingException {
        return ResponseEntity.ok(orderService.list());
    }

    @GetMapping("/listcustomer")
    public ResponseEntity<?> listcustomer(){
        return ResponseEntity.ok(customerServiceFeign.list().getBody());
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody Order order) throws JsonMappingException {
        Map<String, String> resp = new HashMap<>();
        orderService.modify(order);
        resp.put("Response", "Upload successful");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Map<String, String> resp = new HashMap<>();
        Order order = orderService.getById(id);

        if (order != null){
            return ResponseEntity.ok(order);
        }

        resp.put("Response", "Can't find the object");
        return ResponseEntity.ok(resp);
    }

}
