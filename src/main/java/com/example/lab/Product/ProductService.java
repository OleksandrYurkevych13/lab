package com.example.lab.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProducts();
    List<ProductDTO> findProductsByCategory(Long categoryId);
    void saveProduct(ProductDTO productDTO);


}

