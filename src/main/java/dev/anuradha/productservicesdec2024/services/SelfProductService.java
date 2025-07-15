package dev.anuradha.productservicesdec2024.services;

import dev.anuradha.productservicesdec2024.dtos.AuthResponseDTO;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import dev.anuradha.productservicesdec2024.models.Category;
import dev.anuradha.productservicesdec2024.models.Product;
import dev.anuradha.productservicesdec2024.repositories.CategoryRepository;
import dev.anuradha.productservicesdec2024.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RestTemplate restTemplate;

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
            categoryRepository.save(newCategory);
        }
        else{
            product.setCategory(categoryFromDB);
        }
        Product createdProduct =  productRepository.save(product);
        return createdProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product){
      if(productRepository.existsById(id)){
          product.setId(id);
          Product updatedProduct = productRepository.save(product);
          return updatedProduct;
      }
      else{
          throw new EntityNotFoundException("Product with the given id does not exist");
      }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) {

        return productRepository.findAll(
                PageRequest.of(
                        pageNo,
                        pageSize,
                        Sort.by("title").descending().and(Sort.by("price").ascending())
                )
        );
    }

    @Override
    public Product getDetailsBasedOnUserScope(Long productId, Long userId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            System.out.println("No product found");
            return null;
        }
        //else check the scope of product whether it's is public or private
        AuthResponseDTO authResponseDTO = restTemplate.getForObject("http://userAuthService/users/{userId}",AuthResponseDTO.class, userId);

        if(authResponseDTO == null){
            System.out.println("No user detail found");
            return null;
        }

        System.out.println(authResponseDTO.getEmail());
        return optionalProduct.get();
    }
}
