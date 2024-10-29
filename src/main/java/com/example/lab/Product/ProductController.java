package com.example.lab.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAllProducts();
    }


    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.findProductsByCategory(categoryId);
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createNewProduct(@RequestBody @Validated ProductCreationRequest productCreationRequest) {
        return productService.createProduct(productCreationRequest);
    }


    @PostMapping
    public void saveProduct(@RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
    }

  
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
    @GetMapping(params = {"category"})
    public List<ProductDTO> getProductsByCategoryParam(@RequestParam String category) {

        return productService.findProductsByCategoryName(category);
    }

}
