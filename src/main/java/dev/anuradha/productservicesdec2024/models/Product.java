package dev.anuradha.productservicesdec2024.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private double price;

    public Product(Long id, String title, String description, String imageUrl, Category category, double price) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
    }

    public Product() {

    }
}

/*
Cardinality
Product     Category
1           1
M           1
Cardinality of P:C -- > m:1
Therefore store category id on product side
 */
