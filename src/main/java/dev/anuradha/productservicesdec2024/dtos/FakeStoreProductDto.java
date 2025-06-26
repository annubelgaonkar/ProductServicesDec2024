package dev.anuradha.productservicesdec2024.dtos;

import dev.anuradha.productservicesdec2024.models.Category;
import dev.anuradha.productservicesdec2024.models.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FakeStoreProductDto {

    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public Product toProduct(){
        Product product = new Product();
        product.setDescription(description);
        product.setTitle(title);
        product.setPrice(price);
        product.setImageUrl(image);
        product.setCategory(new Category());
        return product;
    }

}
