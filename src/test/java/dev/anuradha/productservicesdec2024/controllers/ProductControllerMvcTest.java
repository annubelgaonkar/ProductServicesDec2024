package dev.anuradha.productservicesdec2024.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
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
                .andExpect(content().string(requestBody));
    }
}