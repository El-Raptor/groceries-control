package com.raptor.groceries.service;

import com.raptor.groceries.dto.CategoryRequestDTO;
import com.raptor.groceries.dto.CategoryResponseDTO;
import com.raptor.groceries.model.Category;
import com.raptor.groceries.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        Category category = Category.builder().name(dto.name()).build();
        return toResponseDTO(repository.save(category));
    }

    public List<CategoryResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    private CategoryResponseDTO toResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}