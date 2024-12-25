package dev.anuradha.productservicesdec2024.models;

import lombok.*;
public class Product extends BaseModel {

    private String title;
    private String description;
    private String imageUrl;
    private Category category;
    private double price;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
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
