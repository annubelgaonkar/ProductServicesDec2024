package dev.anuradha.productservicesdec2024.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

/**These are the unit tests for the endpoints which can't be tested through
 * ProductControllerTest
 * These are only for testing if the endpoints can be reached when called or if a
 * call is reaching the controller layer
*/
@WebMvcTest(controllers = ProductController.class)
class ProductControllerMvcTest {

    public void Test_getAllProducts_RunSuccessfully() {

    }
}