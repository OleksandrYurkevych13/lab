package com.example.lab.Product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

// Import dla @Positive

@Data
public final class ProductCreationRequest {

    @NotNull
    private final String name;

    @Positive
    private final double price;

    @NotNull
    private final Long categoryId;

    @JsonCreator
    public ProductCreationRequest(
            @JsonProperty("name") String name,
            @JsonProperty("price") double price,
            @JsonProperty("categoryId") Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
}
