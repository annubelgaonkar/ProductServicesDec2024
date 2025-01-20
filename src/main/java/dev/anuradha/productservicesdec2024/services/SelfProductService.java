package dev.anuradha.productservicesdec2024.services;

import dev.anuradha.productservicesdec2024.dtos.FakeStoreProductDto;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Category;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.repositories.CategoryRepository;
import dev.anuradha.productservicesdec2024.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
                    this.productRepository = productRepository;
                    this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException{
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with the given id does not exist");
        }

        return optionalProduct.get();
    }
    @Override
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products;
    }

    //need to implement createProduct
    @Override
    public Product createProduct(String title,
                                 String description,String imageUrl,
                                 String category,
                                 double price){
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);

        Category categoryFromDB = categoryRepository.findByTitle(category);
        if(categoryFromDB == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
        }
        else{
            product.setCategory(categoryFromDB);
        }
        Product createdProduct =  productRepository.save(product);
        return createdProduct;
    }


}
