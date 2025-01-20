package dev.anuradha.productservicesdec2024.repositories;

import dev.anuradha.productservicesdec2024.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

}
