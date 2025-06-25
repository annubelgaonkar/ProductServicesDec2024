package dev.anuradha.productservicesdec2024.controllers;

import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productService;

    @Test
    public void Test_getSingleProductWithPositiveId_ReturnsProductSuccessfully () {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("iPhone 6");

        when(productService.getSingleProduct(2)).thenReturn(product);

        //Act
        Product response = productController.getSingleProduct(id);

        //Assert
        assertNotNull(response);
        assertEquals(product.getTitle(), response.getTitle());
        assertEquals(product.getId(), response.getId());
    }
}