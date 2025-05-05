package dev.anuradha.productservicesdec2024.services;

import dev.anuradha.productservicesdec2024.dtos.CreateProductRequestDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

   Product getSingleProduct(long id) throws ProductNotFoundException;

   Product createProduct(String title,
                         String description,
                         String imageUrl,
                         String category,
                         double price);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> getPaginatedProducts(int pageNo, int pageSize);
}
