package dev.anuradha.productservicesdec2024.controllers;

import dev.anuradha.productservicesdec2024.dtos.CreateProductRequestDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
   // @Qualifier("selfProductService")
    private ProductService productService;

//    public ProductController(@Qualifier("selfProductService") ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    //Pagination
    @GetMapping("/paginated")
    Page<Product> getPaginatedProducts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return productService.getPaginatedProducts(pageNo, pageSize);
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getImageUrl(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getPrice());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}/{userId}")
    public Product getProductDetailsBasedOnUserScope(@PathVariable Long productId,
                                                     @PathVariable Long userId){
        return productService.getDetailsBasedOnUserScope(productId, userId);
    }


}