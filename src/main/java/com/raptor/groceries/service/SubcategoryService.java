package com.raptor.groceries.service;

import com.raptor.groceries.dto.CategoryResponseDTO;
import com.raptor.groceries.dto.SubcategoryRequestDTO;
import com.raptor.groceries.dto.SubcategoryResponseDTO;
import com.raptor.groceries.model.Category;
import com.raptor.groceries.model.Subcategory;
import com.raptor.groceries.repository.CategoryRepository;
import com.raptor.groceries.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

    private final SubcategoryRepository repository;
    private final CategoryRepository categoryRepository;

    public SubcategoryResponseDTO create(SubcategoryRequestDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Subcategory subcategory = Subcategory.builder()
                .name(dto.name())
                .category(category)
                .build();

        return toResponseDTO(repository.save(subcategory));
    }

    public SubcategoryResponseDTO toResponseDTO(Subcategory subcategory) {
        CategoryResponseDTO catDto = new CategoryResponseDTO(
                subcategory.getCategory().getId(),
                subcategory.getCategory().getName()
        );
        return new SubcategoryResponseDTO(subcategory.getId(), subcategory.getName(), catDto);
    }
}