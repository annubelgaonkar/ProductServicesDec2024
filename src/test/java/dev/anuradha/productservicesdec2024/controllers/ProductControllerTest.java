package dev.anuradha.productservicesdec2024.controllers;

import dev.anuradha.productservicesdec2024.dtos.CreateProductRequestDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Category;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        product.setTitle("iPhone");

        when(productService.getSingleProduct(id)).thenReturn(product);

        //Act
        Product response = productController.getSingleProduct(id);

        //Assert
        assertNotNull(response);
        assertEquals(product.getTitle(), response.getTitle());
        assertEquals(product.getId(), response.getId());
    }

    @Test
    public void Test_getSingleProduct_ThrowsProductNotFoundException () {
        Long id = 100L;

        when(productService.getSingleProduct(id)).thenThrow(new ProductNotFoundException("Product not found"));
        assertThrows(ProductNotFoundException.class, () -> productController.getSingleProduct(id));
    }

    @Test
    public void Test_getAllProducts_ReturnsAllProductsSuccessfully () {
        //step 1: Create category objects
        Category electronics = new Category();
        electronics.setId(1L);
        electronics.setTitle("Electronics");

        Category books = new Category();
        books.setId(2L);
        books.setTitle("Books");

        // Step 2: Create list of products using those categories
        List<Product> products = Arrays.asList(
                new Product(1L, "Phone", "desc",
                        "img.jpg", electronics, 500.0),
                new Product(2L, "Book", "desc",
                        "img.jpg", books, 200.00)
        );

        // Step 3: Mock the service to return the list
        when(productService.getAllProducts()).thenReturn(products);

        // Step 4: Call the controller method
        List<Product> response = productController.getAllProducts();

        // Step 5: Assertions to verify behavior
        assertEquals(2, response.size());
        assertEquals("Phone", response.get(0).getTitle());
        assertEquals("Electronics", response.get(0).getCategory().getTitle());
    }

    @Test
    public void Test_createProductSuccessfully () {
        //Step 1: create category object
        Category electronics = new Category();
        electronics.setId(1L);
        electronics.setTitle("Electronics");

        // Step 2: Create the input DTO (this mimics client input as a JSON request)
        CreateProductRequestDto requestDto = new CreateProductRequestDto(
                "Camera","Nice camera",
                "img.jpg","Electronics", 400.0);

        // Step 3: Create the expected Product that the service would return
        Product savedProduct = new Product(1L, "Camera","Nice camera",
                "img.jpg",electronics, 400.0);

        // Step 4: Mock the service call (simulate how the backend behaves)
        when(productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getImageUrl(),
                requestDto.getCategory(),
                requestDto.getPrice())).thenReturn(savedProduct);

        // Step 5: Call the controller with the request DTO
        Product response = productController.createProduct(requestDto);

        // Step 6: Assert
        assertNotNull(response);
        assertEquals("Camera", response.getTitle());
        assertEquals(400.0, response.getPrice());
        assertEquals("Electronics", response.getCategory().getTitle());
    }

    @Test
    public void Test_updateProductSuccessfully () {
        Long id = 2L;
        //Create category
        Category electronics = new Category();
        electronics.setId(1L);
        electronics.setTitle("Electronics");

        // Step 3: Create the input product (this simulates the request body)
        Product inputProduct = new Product(
                null, "Updated Phone", "Updated desc",
                "img.jpg",electronics, 800);

        // What the service will return after updating
        Product updatedProduct = new Product(
                id, "Updated Phone", "Updated desc",
                "img.jpg", electronics,800);

        // Step 5: Mock the service to return updatedProduct when called
        when(productService.updateProduct(id, inputProduct)).thenReturn(updatedProduct);

        // Step 6: Call the controller with the product update
        var response = productController.updateProduct(id, inputProduct);

        // Step 7: Assertions
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Updated Phone", response.getBody().getTitle());
    }

    @Test
    public void Test_updateProduct_NotFound() {
        Long id = 999L;

        //create a category
        Category electronics = new Category();
        electronics.setId(1L);
        electronics.setTitle("Electronics");

        // Step 3: Create the input product (like request body sent from client side)
        Product inputProduct = new Product(null, "Updated", "desc", "img.jpg",
                electronics, 800);

        // Step 4: Mock the service to return null (simulates product not found)
        when(productService.updateProduct(id, inputProduct)).thenReturn(null);

        // Step 5: Call the controller with this non-existing product ID
        var response = productController.updateProduct(id, inputProduct);

        // Step 6: Assert that the response is 404 Not Found or 400
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void Test_deleteProductSuccessfully () {
        Long id = 2L;

        // Step 2: Mock service to do nothing (void method)
        doNothing().when(productService).deleteProduct(id);

        // Step 3: Call the controller
        ResponseEntity<Void> response = productController.deleteProduct(id);

        // Step 4: Assertions
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void Test_deleteProductThrowsProductNotFound() throws ProductNotFoundException {
        // Step 1: Invalid random product ID
        Long id = 1000L;

        // Step 2: Mock the service to throw ProductNotFoundException
        doThrow(new ProductNotFoundException("Product not found")).
                when(productService).deleteProduct(id);

        // Step 3: Try to delete and catch exception since the product ID is invalid
        assertThrows(ProductNotFoundException.class,
                () -> productController.deleteProduct(id)
        );

    }

}