package com.example.lab.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProducts();
    List<ProductDTO> findProductsByCategory(Long categoryId);
    void saveProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> findProductsByCategoryName(String categoryName);
    ProductDTO createProduct(ProductCreationRequest productCreationRequest);
}

