package dev.anuradha.productservicesdec2024;

import dev.anuradha.productservicesdec2024.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServicesDec2024Application {

    public static void main(String[] args) {
        Product p1 = new Product();
        
        SpringApplication.run(ProductServicesDec2024Application.class, args);
    }

}
