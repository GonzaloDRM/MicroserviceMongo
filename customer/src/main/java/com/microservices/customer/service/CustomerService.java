package com.microservices.customer.service;

import com.microservices.customer.model.Customer;
import com.microservices.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> listAll(){
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getById(Integer id){
        return customerRepository.findById(id).orElse(null);
    }

}
