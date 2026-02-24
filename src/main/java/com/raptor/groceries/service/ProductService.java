package com.raptor.groceries.service;

import com.raptor.groceries.dto.*;
import com.raptor.groceries.model.Brand;
import com.raptor.groceries.model.Product;
import com.raptor.groceries.model.Subcategory;
import com.raptor.groceries.repository.BrandRepository;
import com.raptor.groceries.repository.ProductRepository;
import com.raptor.groceries.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final BrandRepository brandRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryService subcategoryService;

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        Subcategory subcategory = subcategoryRepository.findById(dto.subcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategoria não encontrada"));

        Product product = Product.builder()
                .name(dto.name())
                .brand(brand)
                .subcategory(subcategory)
                .build();

        return toResponseDTO(repository.save(product));
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        BrandResponseDTO brandDto = new BrandResponseDTO(product.getBrand().getId(), product.getBrand().getName());
        SubcategoryResponseDTO subDto = subcategoryService.toResponseDTO(product.getSubcategory());

        return new ProductResponseDTO(product.getId(), product.getName(), brandDto, subDto);
    }
}