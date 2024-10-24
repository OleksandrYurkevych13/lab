package com.example.lab.Product;

import com.example.lab.Category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. Pobranie wszystkich produktów
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAllProducts();
    }

    // 2. Pobranie produktów z wybranej kategorii
    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.findProductsByCategory(categoryId);
    }

    // 3. Zapis nowego produktu na podstawie ProductDTO
    @PostMapping
    public void saveProduct(@RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
    }

    // 4. Zapis nowego produktu na podstawie ProductRequest (opcjonalne uproszczenie)
    @PostMapping("/create")
    public void createProduct(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam Long categoryId) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setCategoryId(categoryId);

        productService.saveProduct(productDTO);
    }
}