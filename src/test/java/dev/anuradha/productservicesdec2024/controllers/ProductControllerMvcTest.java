package dev.anuradha.productservicesdec2024.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anuradha.productservicesdec2024.dtos.CreateProductRequestDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Category;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**These are the unit tests for the endpoints which can't be tested through
 * ProductControllerTest.
 * These are only for testing if the endpoints can be reached when called or if a
 * call is reaching the controller layer.
 * Testing only the controller layer without loading the full Spring context.
*/
@WebMvcTest(controllers = ProductController.class)
class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void Test_getAllProducts_RunSuccessfully() throws Exception {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("iPhone");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getAllProducts()).thenReturn(productList);

        String requestBody = mapper.writeValueAsString(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(requestBody));
    }
    @Test
    public void Test_getProductById_ReturnsProductSuccessfully() throws Exception {
        //Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("iPhone");

        List<Product> productList = List.of(product);
        when(productService.getSingleProduct(id)).thenReturn(product);

        /** We convert the list of products to JSON string, because
          * the controller returns JSON, and we need to match it in the assertion.
          */
        String expectedJson = mapper.writeValueAsString(product);

        //Act and assert
        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void Test_getProductById_ReturnsProductNotFound() throws Exception {
        Long id = 999L;
        when(productService.getSingleProduct(id)).thenThrow(new ProductNotFoundException("Product not found"));

        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void Test_createProduct_ReturnsCreatedProductSuccessfully() throws Exception {
        //Arrange
        CreateProductRequestDto requestDto = new CreateProductRequestDto(
                "Camera","Nice camera", "img.jpg",
                "Electronics",500.0
        );
        Category electronics = new Category();
        electronics.setTitle("Electronics");

        Product createdProduct = new Product(1L, "Camera","Nice camera",
                "img.jpg",electronics,500.0);

        when(productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getImageUrl(),
                requestDto.getCategory(),
                requestDto.getPrice())).thenReturn(createdProduct);

        String requestJson = mapper.writeValueAsString(requestDto);
        String responseJson = mapper.writeValueAsString(createdProduct);

        //Act & Assert
        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .post("/products")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJson));
    }

    @Test
    public void Test_deleteProduct_DeleteProductSuccessfully() throws Exception {
        Long id = 1L;

        doNothing().when(productService).deleteProduct(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
        .delete("/products/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void Test_deleteProduct_ReturnsProductNotFound() throws Exception {
        Long id = 999L;

        doThrow(new ProductNotFoundException("Product not found"))
                .when(productService).deleteProduct(id);

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
        .delete("/products/{id}", id))
                .andExpect(status().isNotFound());
    }
}