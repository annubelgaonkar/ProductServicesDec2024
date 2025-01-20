package dev.anuradha.productservicesdec2024.services;

import dev.anuradha.productservicesdec2024.dtos.CreateProductRequestDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

   Product getSingleProduct(long id) throws ProductNotFoundException;

   Product createProduct(String title,
                         String description,
                         String imageUrl,
                         String category,
                         double price);
}
