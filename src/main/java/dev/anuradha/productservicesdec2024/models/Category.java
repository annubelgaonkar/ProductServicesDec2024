package dev.anuradha.productservicesdec2024.models;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Category extends BaseModel{

    private String title;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Product> products;

}
