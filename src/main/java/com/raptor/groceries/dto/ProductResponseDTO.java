package com.raptor.groceries.dto;

public record ProductResponseDTO(
        long id,
        String name,
        BrandResponseDTO brand,
        SubcategoryResponseDTO subcategory
) {
}