package dev.anuradha.productservicesdec2024.repositories;

import dev.anuradha.productservicesdec2024.models.Category;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.projections.ProductWithIdAndPriceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();

    @Override
    Page<Product> findAll(Pageable pageable);

    Product save(Product p);

    @Override
    Optional<Product> findById(Long id);

    List<Product> findByCategory(Category category);

    List<Product> findAllByCategory_Title(String title);
    List<Product> findAllByCategory_Id(Long id);

    @Query("select p.id, p.price FROM Product p where p.category.title = :categoryName")
    List<ProductWithIdAndPriceProjection> getProductTitleAndPricesAndGivenCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "select * from products p where p.title = :title", nativeQuery = true)
    List<ProductWithIdAndPriceProjection> getIdAndPricesOfAllProductsWithGivenTile(@Param("title") String title);

}
