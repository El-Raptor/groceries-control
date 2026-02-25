package com.raptor.groceries.service;

import com.raptor.groceries.dto.GroceryStoreRequestDTO;
import com.raptor.groceries.dto.GroceryStoreResponseDTO;
import com.raptor.groceries.model.GroceryStore;
import com.raptor.groceries.repository.GroceryStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryStoreService {

    private final GroceryStoreRepository repository;

    public GroceryStoreResponseDTO create(GroceryStoreRequestDTO dto) {
        GroceryStore store = GroceryStore.builder().name(dto.name()).build();
        return toResponseDTO(repository.save(store));
    }

    public List<GroceryStoreResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public GroceryStoreResponseDTO toResponseDTO(GroceryStore store) {
        return new GroceryStoreResponseDTO(store.getId(), store.getName());
    }
}