package com.raptor.groceries.service;

import com.raptor.groceries.dto.BrandRequestDTO;
import com.raptor.groceries.dto.BrandResponseDTO;
import com.raptor.groceries.model.Brand;
import com.raptor.groceries.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public BrandResponseDTO create(BrandRequestDTO dto) {
        Brand brand = Brand.builder().name(dto.name()).build();
        Brand saved = repository.save(brand);
        return toResponseDTO(saved);
    }

    public List<BrandResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public BrandResponseDTO findById(Long id) {
        Brand brand = repository.findById(id).orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        return toResponseDTO(brand);
    }

    private BrandResponseDTO toResponseDTO(Brand brand) {
        return new BrandResponseDTO(brand.getId(), brand.getName());
    }
}