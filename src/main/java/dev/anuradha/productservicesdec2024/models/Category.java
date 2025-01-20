package dev.anuradha.productservicesdec2024.models;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
public class Category extends BaseModel{

    private String title;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Product> products;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
