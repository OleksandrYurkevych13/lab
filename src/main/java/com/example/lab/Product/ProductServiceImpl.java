package com.example.lab.Product;

import com.example.lab.Category.Category;
import com.example.lab.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    // Metoda do zapisywania produktu za pomocą ProductDTO
    @Override
    public void saveProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product.setDateAdded(LocalDate.now());
        productRepository.save(product);
    }

    // Metoda do tworzenia nowego produktu za pomocą ProductCreationRequest
    @Override
    public ProductDTO createProduct(ProductCreationRequest productCreationRequest) {
        Product product = new Product();
        product.setName(productCreationRequest.getName());
        product.setPrice(productCreationRequest.getPrice());
        product.setDateAdded(LocalDate.now()); // Ustawiamy datę dodania

        // Pobierz kategorię lub utwórz nową
        Category category = categoryRepository.findById(productCreationRequest.getCategoryId())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setId(productCreationRequest.getCategoryId());
                    newCategory.setName("Nowa Kategoria"); // Możesz ustawić domyślną nazwę
                    return categoryRepository.save(newCategory);
                });

        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductDTO> findProductsByCategoryName(String categoryName) {
        // Znajdź kategorię na podstawie nazwy
        Category category = categoryRepository.findByName(categoryName);

        // Jeśli kategoria istnieje, znajdź produkty związane z tą kategorią
        if (category != null) {
            List<Product> products = productRepository.findByCategoryId(category.getId());
            return products.stream()
                    .map(productMapper::toDto)
                    .collect(Collectors.toList());
        }

        // Jeśli kategoria nie istnieje, zwróć pustą listę
        return Collections.emptyList();
    }
}
