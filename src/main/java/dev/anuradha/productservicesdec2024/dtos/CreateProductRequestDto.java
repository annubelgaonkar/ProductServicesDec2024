package dev.anuradha.productservicesdec2024.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class CreateProductRequestDto {

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Image URL is required")
    private String imageUrl;
    @NotBlank(message = "Category is required")
    private String category;
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    private double price;


}
