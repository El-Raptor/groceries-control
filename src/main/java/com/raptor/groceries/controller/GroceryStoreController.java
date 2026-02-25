package com.raptor.groceries.controller;

import com.raptor.groceries.dto.GroceryStoreRequestDTO;
import com.raptor.groceries.dto.GroceryStoreResponseDTO;
import com.raptor.groceries.service.GroceryStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class GroceryStoreController {

    private final GroceryStoreService service;

    @PostMapping
    public ResponseEntity<GroceryStoreResponseDTO> create(@RequestBody GroceryStoreRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<GroceryStoreResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}