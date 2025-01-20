package dev.anuradha.productservicesdec2024.controllers;

import dev.anuradha.productservicesdec2024.dtos.CreateProductRequestDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService")ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
         return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getPrice());
    }

    public  void updateProduct() {

    }
}
