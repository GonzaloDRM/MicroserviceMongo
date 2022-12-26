package com.microservices.product;

import com.microservices.product.model.Product;
import com.microservices.product.repository.ProductRepository;
import com.microservices.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProductApplicationTests {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Test
	void contextLoads() {

		Product product = new Product();
		product.setId(1);
		product.setName("Prueba");
		product.setPrice(1.00);
		product.setStock(1);







	}

}
