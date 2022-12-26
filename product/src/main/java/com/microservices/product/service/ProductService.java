package com.microservices.product.service;

import com.microservices.product.model.Product;
import com.microservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> list(){
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Integer id){
        return productRepository.findById(id).orElse(null);
    }

}
