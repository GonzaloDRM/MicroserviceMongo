package com.microservices.order.service;

import com.microservices.order.client.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceFeign {

    @Autowired
    private final ProductFeign productFeign;

    public ProductServiceFeign(ProductFeign productFeign){
        this.productFeign = productFeign;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> list(){
        return productFeign.list();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getById(Integer id){
        return productFeign.getById(id);
    }

}
