package com.microservices.order.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.order.dto.OrderDTO;
import com.microservices.order.model.Customer;
import com.microservices.order.model.Order;
import com.microservices.order.model.Product;
import com.microservices.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private final OrderRepository orderRepository;
    private final ProductServiceFeign productServiceFeign;
    private final CustomerServiceFeign customerServiceFeign;

    public OrderService(OrderRepository orderRepository, ProductServiceFeign productServiceFeign, CustomerServiceFeign customerServiceFeign) {
        this.orderRepository = orderRepository;
        this.productServiceFeign = productServiceFeign;
        this.customerServiceFeign = customerServiceFeign;
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> list() throws JsonMappingException {
        List<OrderDTO> resp = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        List<Product> products;
        Product product = new Product();
        OrderDTO orderDTO = new OrderDTO();
        Customer customer = new Customer();
        ObjectMapper objectMapper = new ObjectMapper();
    //    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for (Order order : orders) {
            products = new ArrayList<>();
            for (Integer idProduct : order.getProducts()) {
                product = objectMapper.updateValue(product, productServiceFeign.getById(idProduct).getBody());

                if (product != null) {
                    products.add(product);
                }
                orderDTO.setProducts(products);
            }
            logger.info(customerServiceFeign.getById(order.getCustomer()).getBody().toString());
            customer = objectMapper.updateValue(customer, customerServiceFeign.getById(order.getCustomer()).getBody());
            if (customer != null) {
                orderDTO.setCustomer(customer);
            }
            orderDTO.setId(order.getId());
            orderDTO.setState(order.getState());
            orderDTO.setCreationDate(order.getCreationDate());
            orderDTO.setPaymentMethod(order.getPaymentMethod());
            orderDTO.setUpdateDate(order.getUpdateDate());
            resp.add(orderDTO);
        }

        return resp;
    }

    @Transactional
    public void modify(Order order) throws JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order toModify = orderRepository.findById(order.getId()).orElse(null);

        if (toModify != null){
            orderRepository.save(objectMapper.updateValue(toModify, order));
        }

    }

    @Transactional(readOnly = true)
    public Order getById(Integer id){
        return orderRepository.findById(id).orElse(null);
    }

}
